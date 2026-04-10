import tkinter as tk
from tkinter import ttk, messagebox
import sqlite3

class ViewReservationsWindow:
    def __init__(self, master, go_back_callback):
        self.master = master
        self.go_back_callback = go_back_callback
        self.master.title("View Reservations")

        title = tk.Label(master, text="Reservations List",
                         font=("Arial", 18, "bold"), fg="#003366")
        title.pack(pady=10)

        # Table to display reservations
        self.table = ttk.Treeview(
            master,
            columns=("reservation_id", "client_name", "car_info",
                     "start_date", "end_date", "payment_status",
                     "return_status", "total_cost"),
            show="headings",
            height=12
        )

        headings = ["Reservation ID", "Client Name", "Car",
                    "Start Date", "End Date", "Payment Status",
                    "Return Status", "Total Cost"]
        for col, heading in zip(self.table["columns"], headings):
            self.table.heading(col, text=heading)
            self.table.column(col, width=120)

        self.table.pack(pady=10, fill='both', expand=True)

        btn_frame = tk.Frame(master)
        btn_frame.pack(pady=5)

        tk.Button(btn_frame, text="Refresh", command=self.load_reservations).grid(row=0, column=0, padx=5)
        tk.Button(btn_frame, text="Mark as Paid", command=self.mark_as_paid).grid(row=0, column=1, padx=5)
        tk.Button(btn_frame, text="Mark as Returned", command=self.mark_as_returned).grid(row=0, column=2, padx=5)
        tk.Button(btn_frame, text="Back", command=self.go_back_callback).grid(row=0, column=3, padx=5)

        self.load_reservations()

    def load_reservations(self):
        self.table.delete(*self.table.get_children())

        conn = sqlite3.connect("car_rental.db")
        c = conn.cursor()

        # Ensure the correct tables and schema
        c.execute('''CREATE TABLE IF NOT EXISTS clients (
                        client_id TEXT PRIMARY KEY,
                        name TEXT,
                        email TEXT,
                        contact_number TEXT,
                        license_number TEXT,
                        address TEXT
                    )''')
        c.execute('''CREATE TABLE IF NOT EXISTS cars (
                        car_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        type TEXT,
                        model TEXT,
                        location TEXT,
                        rental_rate REAL
                    )''')
        c.execute('''CREATE TABLE IF NOT EXISTS reservations (
                        reservation_id TEXT PRIMARY KEY,
                        client_id TEXT,
                        car_id INTEGER,
                        start_date TEXT,
                        end_date TEXT,
                        payment_status TEXT,
                        return_status TEXT,
                        total_cost REAL,
                        FOREIGN KEY(client_id) REFERENCES clients(client_id),
                        FOREIGN KEY(car_id) REFERENCES cars(car_id)
                    )''')

        # Fetch reservations with client name and car model
        c.execute('''
            SELECT r.reservation_id,
                   cl.name AS client_name,
                   ca.type || ' ' || ca.model AS car_info,
                   r.start_date,
                   r.end_date,
                   r.payment_status,
                   r.return_status,
                   r.total_cost
            FROM reservations r
            JOIN clients cl ON r.client_id = cl.client_id
            JOIN cars ca ON r.car_id = ca.car_id
        ''')
        rows = c.fetchall()
        conn.close()

        if not rows:
            messagebox.showinfo("Info", "No reservations found in the database.")
            return

        for row in rows:
            self.table.insert("", tk.END, values=row)

    def get_selected_reservation_id(self):
        selected = self.table.selection()
        if not selected:
            messagebox.showwarning("Selection Error", "Please select a reservation.")
            return None
        reservation_id = self.table.item(selected[0])["values"][0]
        return reservation_id

    def mark_as_paid(self):
        res_id = self.get_selected_reservation_id()
        if not res_id:
            return
        conn = sqlite3.connect("car_rental.db")
        c = conn.cursor()
        c.execute("UPDATE reservations SET payment_status = 'Paid' WHERE reservation_id = ?", (res_id,))
        conn.commit()
        conn.close()
        messagebox.showinfo("Success", f"Reservation {res_id} marked as Paid.")
        self.load_reservations()

    def mark_as_returned(self):
        res_id = self.get_selected_reservation_id()
        if not res_id:
            return
        conn = sqlite3.connect("car_rental.db")
        c = conn.cursor()
        c.execute("UPDATE reservations SET return_status = 'Returned' WHERE reservation_id = ?", (res_id,))
        conn.commit()
        conn.close()
        messagebox.showinfo("Success", f"Reservation {res_id} marked as Returned.")
        self.load_reservations()
