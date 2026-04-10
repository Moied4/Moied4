import tkinter as tk
from tkinter import messagebox
import sqlite3
from datetime import datetime

class SearchWindow:
    def __init__(self, master, client_id, on_car_selected):
        self.master = master
        self.client_id = client_id
        self.on_car_selected = on_car_selected
        self.master.title("Search Cars")

        # ---------- Input fields ----------
        tk.Label(master, text="Car Type:").grid(row=0, column=0, sticky='e')
        self.car_type_entry = tk.Entry(master)
        self.car_type_entry.grid(row=0, column=1)

        tk.Label(master, text="Model:").grid(row=1, column=0, sticky='e')
        self.model_entry = tk.Entry(master)
        self.model_entry.grid(row=1, column=1)

        tk.Label(master, text="Rental Start Date (YYYY-MM-DD):").grid(row=2, column=0, sticky='e')
        self.start_date_entry = tk.Entry(master)
        self.start_date_entry.grid(row=2, column=1)

        tk.Label(master, text="Rental End Date (YYYY-MM-DD):").grid(row=3, column=0, sticky='e')
        self.end_date_entry = tk.Entry(master)
        self.end_date_entry.grid(row=3, column=1)

        tk.Label(master, text="Location:").grid(row=4, column=0, sticky='e')
        self.location_entry = tk.Entry(master)
        self.location_entry.grid(row=4, column=1)

        tk.Button(master, text="Search", command=self.search_cars).grid(row=5, column=0, columnspan=2, pady=10)

        # ---------- Results ----------
        self.result_listbox = tk.Listbox(master, width=80)
        self.result_listbox.grid(row=6, column=0, columnspan=2)
        self.result_listbox.bind('<<ListboxSelect>>', self.on_select)

        self.available_cars = []

    # ---------- Search Logic ----------
    def search_cars(self):
        car_type = self.car_type_entry.get().strip()
        model = self.model_entry.get().strip()
        start_date_str = self.start_date_entry.get().strip()
        end_date_str = self.end_date_entry.get().strip()
        location = self.location_entry.get().strip()

        # Validate dates
        if start_date_str and end_date_str:
            try:
                start_date = datetime.strptime(start_date_str, "%Y-%m-%d")
                end_date = datetime.strptime(end_date_str, "%Y-%m-%d")
                if start_date > end_date:
                    messagebox.showerror("Date Error", "Start date must be before or equal to end date.")
                    return
            except ValueError:
                messagebox.showerror("Date Error", "Dates must be in YYYY-MM-DD format.")
                return
        elif start_date_str or end_date_str:
            messagebox.showerror("Date Error", "Please enter both start and end dates or leave both empty.")
            return
        else:
            start_date = end_date = None

        # ---------- Database query ----------
        conn = sqlite3.connect('car_rental.db')
        c = conn.cursor()

        # Base query with filters
        query = '''
            SELECT car_id, type, model, location, rental_rate FROM cars
            WHERE (? = '' OR LOWER(type) LIKE LOWER(?))
              AND (? = '' OR LOWER(model) LIKE LOWER(?))
              AND (? = '' OR LOWER(location) LIKE LOWER(?))
        '''
        params = (
            car_type, f'%{car_type}%',
            model, f'%{model}%',
            location, f'%{location}%'
        )

        # Exclude cars already reserved for overlapping dates
        if start_date and end_date:
            query += '''
              AND car_id NOT IN (
                  SELECT car_id FROM reservations
                  WHERE NOT (end_date < ? OR start_date > ?)
              )
            '''
            params += (start_date_str, end_date_str)

        c.execute(query, params)
        rows = c.fetchall()
        conn.close()

        # ---------- Display results ----------
        self.available_cars = rows
        self.result_listbox.delete(0, tk.END)

        if not rows:
            self.result_listbox.insert(tk.END, "No cars available for the selected criteria.")
            return

        for idx, (car_id, ctype, cmodel, loc, rate) in enumerate(rows):
            self.result_listbox.insert(
                tk.END,
                f"ID: {car_id} | {ctype} {cmodel} | Location: {loc} | Rate: ${rate:.2f}/day"
            )

    # ---------- Handle selection ----------
    def on_select(self, event):
        selection = event.widget.curselection()
        if not selection:
            return
        index = selection[0]
        if index >= len(self.available_cars):
            return

        car = self.available_cars[index]
        car_id = car[0]
        start_date = self.start_date_entry.get().strip()
        end_date = self.end_date_entry.get().strip()

        if not start_date or not end_date:
            messagebox.showwarning("Input Error", "Please enter both rental start and end dates before selecting a car.")
            return

        # Call callback to proceed to reservation
        self.on_car_selected(car_id, start_date, end_date)
