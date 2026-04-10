import tkinter as tk
from tkinter import ttk, messagebox
import sqlite3

class ViewClientsWindow:
    def __init__(self, master, go_back_callback):
        self.master = master
        self.go_back_callback = go_back_callback
        self.master.title("View Clients")

        title = tk.Label(master, text="Client List", font=("Arial", 18, "bold"), fg="#003366")
        title.pack(pady=10)

        # Table to display clients
        self.table = ttk.Treeview(
            master,
            columns=("client_id", "name", "email", "contact_number", "license_number", "address"),
            show="headings",
            height=12
        )
        self.table.heading("client_id", text="Client ID")
        self.table.heading("name", text="Name")
        self.table.heading("email", text="Email")
        self.table.heading("contact_number", text="Contact Number")
        self.table.heading("license_number", text="License Number")
        self.table.heading("address", text="Address")

        for col in self.table["columns"]:
            self.table.column(col, width=120)
        self.table.pack(pady=10, fill='both', expand=True)

        # Buttons
        btn_frame = tk.Frame(master)
        btn_frame.pack(pady=5)

        refresh_btn = tk.Button(btn_frame, text="Refresh", command=self.load_clients)
        refresh_btn.grid(row=0, column=0, padx=5)

        back_btn = tk.Button(btn_frame, text="Back", command=self.go_back_callback)
        back_btn.grid(row=0, column=1, padx=5)

        # Load clients initially
        self.load_clients()

    def load_clients(self):
        """Load all clients from the database into the table."""
        self.table.delete(*self.table.get_children())

        conn = sqlite3.connect("car_rental.db")
        c = conn.cursor()

        # Ensure table exists and matches schema
        c.execute('''
            CREATE TABLE IF NOT EXISTS clients (
                client_id TEXT PRIMARY KEY,
                name TEXT,
                email TEXT,
                contact_number TEXT,
                license_number TEXT,
                address TEXT
            )
        ''')

        # Query with correct columns
        c.execute("SELECT client_id, name, email, contact_number, license_number, address FROM clients")
        rows = c.fetchall()
        conn.close()

        if not rows:
            messagebox.showinfo("Info", "No clients found in the database.")
            return

        for row in rows:
            self.table.insert("", tk.END, values=row)
