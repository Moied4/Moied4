import tkinter as tk
from tkinter import messagebox
import sqlite3
from datetime import datetime
import uuid

class ReservationWindow:
    def __init__(self, master, client_id, car_id, start_date, end_date, on_reservation_done):
        self.master = master
        self.client_id = client_id
        self.car_id = car_id
        self.start_date = start_date
        self.end_date = end_date
        self.on_reservation_done = on_reservation_done

        self.master.title("Reservation Details")

        # Fetch car info & rate
        try:
            conn = sqlite3.connect('car_rental.db')
            c = conn.cursor()
            c.execute("SELECT type, model, rental_rate FROM cars WHERE car_id = ?", (car_id,))
            car = c.fetchone()
        except sqlite3.Error as e:
            messagebox.showerror("Database Error", f"Error fetching car details:\n{e}")
            self.master.destroy()
            return
        finally:
            conn.close()

        if not car:
            messagebox.showerror("Error", "Car not found.")
            self.master.destroy()
            return

        self.car_type, self.car_model, self.rental_rate = car

        # Display reservation info
        tk.Label(master, text=f"Car: {self.car_type} {self.car_model}").pack(pady=5)
        tk.Label(master, text=f"Rental Rate: ${self.rental_rate:.2f} per day").pack(pady=5)
        tk.Label(master, text=f"Start Date: {start_date}").pack(pady=5)
        tk.Label(master, text=f"End Date: {end_date}").pack(pady=5)

        tk.Button(master, text="Confirm Reservation", command=self.confirm_reservation).pack(pady=20)

    def confirm_reservation(self):
        try:
            conn = sqlite3.connect('car_rental.db')
            c = conn.cursor()

            # Check if car is still available
            c.execute('''
                SELECT COUNT(*) FROM reservations
                WHERE car_id = ?
                  AND NOT (end_date < ? OR start_date > ?)
            ''', (self.car_id, self.start_date, self.end_date))
            overlap_count = c.fetchone()[0]

            if overlap_count > 0:
                messagebox.showerror("Unavailable", "Sorry, the car is no longer available for the selected dates.")
                return

            # Calculate total cost
            start_dt = datetime.strptime(self.start_date, "%Y-%m-%d")
            end_dt = datetime.strptime(self.end_date, "%Y-%m-%d")
            days = (end_dt - start_dt).days + 1
            total_cost = days * self.rental_rate

            # Generate unique reservation ID
            reservation_id = str(uuid.uuid4())[:8]

            # Insert reservation into DB
            c.execute('''
                INSERT INTO reservations 
                (reservation_id, client_id, car_id, start_date, end_date, payment_status, return_status, total_cost)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            ''', (
                reservation_id,
                self.client_id,
                self.car_id,
                self.start_date,
                self.end_date,
                "Pending",
                "Not Returned",
                total_cost
            ))
            conn.commit()

        except sqlite3.Error as e:
            messagebox.showerror("Database Error", f"Failed to save reservation:\n{e}")
            return
        finally:
            conn.close()

        # Show confirmation
        messagebox.showinfo(
            "Reservation Confirmed",
            f"Reservation ID: {reservation_id}\n"
            f"Car: {self.car_type} {self.car_model}\n"
            f"Period: {self.start_date} to {self.end_date}\n"
            f"Total Cost: ${total_cost:.2f}"
        )

        # Close window and return to search/main
        self.master.destroy()
        self.on_reservation_done()
