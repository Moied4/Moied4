import tkinter as tk
from tkinter import messagebox
import sqlite3
from database import get_connection

class RegisterWindow:
    def __init__(self, root, go_back_callback):
        self.root = root
        self.go_back_callback = go_back_callback
        self.frame = tk.Frame(root)
        self.frame.pack(fill='both', expand=True)
        self.build_ui()

    def build_ui(self):
        tk.Label(self.frame, text="Register New Client", font=("Arial", 16)).pack(pady=10)

        labels = ['Client ID', 'Name', 'Email', 'Contact Number', 'License Number', 'Address']
        self.entries = {}

        for label in labels:
            tk.Label(self.frame, text=label).pack()
            entry = tk.Entry(self.frame)
            entry.pack()
            self.entries[label] = entry

        tk.Button(self.frame, text="Register", command=self.register_client).pack(pady=10)
        tk.Button(self.frame, text="Back to Login", command=self.go_back_callback).pack()

    def register_client(self):
        client_id = self.entries['Client ID'].get()
        name = self.entries['Name'].get()
        email = self.entries['Email'].get()
        contact = self.entries['Contact Number'].get()
        license_number = self.entries['License Number'].get()
        address = self.entries['Address'].get()

        if not all([client_id, name, email, contact, license_number, address]):
            messagebox.showerror("Error", "All fields are required")
            return

        conn = get_connection()
        c = conn.cursor()
        try:
            c.execute('''
                INSERT INTO clients (client_id, name, email, contact_number, license_number, address)
                VALUES (?, ?, ?, ?, ?, ?)
            ''', (client_id, name, email, contact, license_number, address))
            conn.commit()
            messagebox.showinfo("Success", "Client registered successfully!")
            self.go_back_callback()
        except sqlite3.IntegrityError:
            messagebox.showerror("Error", "Client ID already exists")
        finally:
            conn.close()
