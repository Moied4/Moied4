import tkinter as tk
from tkinter import messagebox
import sqlite3

class AddCarWindow:
    def __init__(self, master, go_back_callback):
        self.master = master
        self.go_back_callback = go_back_callback
        self.master.title("Add New Car")

        tk.Label(master, text="Add New Car", font=("Arial", 18, "bold")).pack(pady=10)

        form_frame = tk.Frame(master)
        form_frame.pack(pady=10)

        labels = ["Car Type", "Model", "Location", "Rental Rate"]
        self.entries = {}

        for i, label_text in enumerate(labels):
            lbl = tk.Label(form_frame, text=label_text, font=("Arial", 11))
            lbl.grid(row=i, column=0, padx=5, pady=5, sticky="e")

            entry = tk.Entry(form_frame, width=30)
            entry.grid(row=i, column=1, padx=5, pady=5)
            self.entries[label_text] = entry

        # Buttons
        btn_frame = tk.Frame(master)
        btn_frame.pack(pady=10)

        add_btn = tk.Button(btn_frame, text="Add Car", command=self.add_car)
        add_btn.grid(row=0, column=0, padx=5)

        back_btn = tk.Button(btn_frame, text="Back", command=self.go_back_callback)
        back_btn.grid(row=0, column=1, padx=5)

    def add_car(self):
        car_type = self.entries["Car Type"].get().strip()
        model = self.entries["Model"].get().strip()
        location = self.entries["Location"].get().strip()
        rental_rate = self.entries["Rental Rate"].get().strip()

        if not all([car_type, model, location, rental_rate]):
            messagebox.showerror("Error", "Please fill in all fields.")
            return

        try:
            rental_rate = float(rental_rate)
        except ValueError:
            messagebox.showerror("Error", "Rental rate must be a number.")
            return

        conn = sqlite3.connect("car_rental.db")
        c = conn.cursor()

        # Create table if it doesn't exist
        c.execute('''CREATE TABLE IF NOT EXISTS cars (
                        car_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        type TEXT,
                        model TEXT,
                        location TEXT,
                        rental_rate REAL
                    )''')

        c.execute(
            "INSERT INTO cars (type, model, location, rental_rate) VALUES (?, ?, ?, ?)",
            (car_type, model, location, rental_rate)
        )
        conn.commit()
        conn.close()

        messagebox.showinfo("Success", f"{car_type} {model} added successfully!")
        self.clear_entries()

    def clear_entries(self):
        for entry in self.entries.values():
            entry.delete(0, tk.END)
