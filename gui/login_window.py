import tkinter as tk
from tkinter import messagebox, simpledialog
import sqlite3
from database import get_connection  #database.py

class LoginWindow:
    def __init__(self, master, on_login_success, on_register_click, on_manager_login):
        """
        Client login window with Manager Login option.
        """
        self.master = master
        self.on_login_success = on_login_success
        self.on_register_click = on_register_click
        self.on_manager_login = on_manager_login

        self.master.title("Car Rental Login")

        # ---------- UI ----------
        tk.Label(master, text="Enter Client ID:", font=("Arial", 12)).pack(pady=10)
        self.client_id_entry = tk.Entry(master, width=30)
        self.client_id_entry.pack(pady=5)

        # Login button
        tk.Button(
            master,
            text="Login",
            width=15,
            bg="#0066CC",
            fg="white",
            command=self.login
        ).pack(pady=10)

        # Register button
        tk.Button(
            master,
            text="Register",
            width=15,
            command=self.on_register_click
        ).pack(pady=5)

        # Manager Login button
        tk.Button(
            master,
            text="Manager Login",
            width=15,
            bg="#003366",
            fg="white",
            command=self.manager_login_prompt
        ).pack(pady=15)

    # ---------- Client Login Logic ----------
    def login(self):
        client_id = self.client_id_entry.get().strip()
        if not client_id:
            messagebox.showwarning("Input Error", "Please enter your Client ID")
            return

        conn = get_connection()
        c = conn.cursor()
        c.execute("SELECT name FROM clients WHERE client_id = ?", (client_id,))
        result = c.fetchone()
        conn.close()

        if result:
            messagebox.showinfo("Login Successful", f"Welcome back, {result[0]}!")
            self.on_login_success(client_id)
        else:
            messagebox.showerror("Login Failed", "Client ID not found.")

    # ---------- Manager Login Logic ----------
    def manager_login_prompt(self):
        """Ask for manager username and password before granting access."""
        username = simpledialog.askstring("Manager Login", "Enter manager ID:")
        if username is None:
            return  # user cancelled

        password = simpledialog.askstring("Manager Login", "Enter password:", show="*")
        if password is None:
            return  # user cancelled

        # Check manager credentials in SQLite
        conn = get_connection()
        c = conn.cursor()
        c.execute("SELECT name FROM managers WHERE manager_id = ? AND password = ?", (username, password))
        result = c.fetchone()
        conn.close()

        if result:
            messagebox.showinfo("Access Granted", f"Welcome, Manager {result[0]}!")
            self.on_manager_login()
        else:
            messagebox.showerror("Access Denied", "Invalid manager credentials.")
