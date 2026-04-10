import tkinter as tk
from tkinter import ttk, messagebox
import sqlite3
from database import get_connection

class ReturnWindow:
    def __init__(self, root, go_back_callback):
        self.root = root
        self.go_back_callback = go_back_callback

        # Ensure the returns table exists
        conn = get_connection()
        c = conn.cursor()
        c.execute('''
            CREATE TABLE IF NOT EXISTS returns (
                return_id     TEXT    PRIMARY KEY,
                reservation_id TEXT   NOT NULL,
                car_id        INTEGER NOT NULL,
                return_date   TEXT    NOT NULL,
                condition     TEXT    NOT NULL,
                overdue_days  INTEGER NOT NULL,
                FOREIGN KEY(reservation_id) REFERENCES reservations(reservation_id),
                FOREIGN KEY(car_id)        REFERENCES cars(car_id)
            )
        ''')
        conn.commit()
        conn.close()

        self.build_ui()
        self.load_returns()

    def build_ui(self):
        self.root.title("Car Return Management")

        title = tk.Label(self.root, text="Car Return Management",
                         font=("Arial", 18, "bold"),
                         fg="#003366")
        title.pack(pady=20)

        form_frame = tk.Frame(self.root)
        form_frame.pack(pady=10)

        labels = [
            ("Return ID", "return_id"),
            ("Reservation ID", "reservation_id"),
            ("Car ID", "car_id"),
            ("Return Date (YYYY‑MM‑DD)", "return_date"),
            ("Condition", "condition"),
            ("Overdue Days", "overdue_days")
        ]
        self.entries = {}
        for i, (lbl_text, key) in enumerate(labels):
            lbl = tk.Label(form_frame, text=lbl_text, font=("Arial", 11))
            lbl.grid(row=i, column=0, padx=5, pady=5, sticky="e")
            entry = tk.Entry(form_frame, width=30)
            entry.grid(row=i, column=1, padx=5, pady=5)
            self.entries[key] = entry

        btn_frame = tk.Frame(self.root)
        btn_frame.pack(pady=10)
        ttk.Button(btn_frame, text="Add Return",    command=self.add_return).grid(row=0, column=0, padx=5)
        ttk.Button(btn_frame, text="Update Return", command=self.update_return).grid(row=0, column=1, padx=5)
        ttk.Button(btn_frame, text="Delete Return", command=self.delete_return).grid(row=0, column=2, padx=5)
        ttk.Button(btn_frame, text="Back",          command=self.go_back_callback).grid(row=0, column=3, padx=5)

        self.table = ttk.Treeview(self.root,
                                  columns=("return_id","reservation_id","car_id","return_date","condition","overdue_days"),
                                  show="headings",
                                  height=8)
        for col in self.table["columns"]:
            self.table.heading(col, text=col.replace("_", " ").title())
            self.table.column(col, width=120)
        self.table.pack(pady=15)
        self.table.bind("<<TreeviewSelect>>", self.on_select_row)

    def load_returns(self):
        self.table.delete(*self.table.get_children())
        conn = get_connection()
        c = conn.cursor()
        c.execute("SELECT return_id, reservation_id, car_id, return_date, condition, overdue_days FROM returns")
        rows = c.fetchall()
        conn.close()

        if not rows:
            # optional: no message each time
            pass

        for row in rows:
            self.table.insert("", tk.END, values=row)

    def on_select_row(self, event):
        selected = self.table.selection()
        if not selected:
            return
        values = self.table.item(selected[0], "values")
        # populate entries for update
        self.entries["return_id"].delete(0, tk.END);         self.entries["return_id"].insert(0, values[0])
        self.entries["reservation_id"].delete(0, tk.END);    self.entries["reservation_id"].insert(0, values[1])
        self.entries["car_id"].delete(0, tk.END);            self.entries["car_id"].insert(0, values[2])
        self.entries["return_date"].delete(0, tk.END);       self.entries["return_date"].insert(0, values[3])
        self.entries["condition"].delete(0, tk.END);         self.entries["condition"].insert(0, values[4])
        self.entries["overdue_days"].delete(0, tk.END);      self.entries["overdue_days"].insert(0, values[5])

    def add_return(self):
        vals = { key: self.entries[key].get().strip() for key in self.entries }
        if any(v == "" for v in vals.values()):
            messagebox.showerror("Error", "Please fill in all fields.")
            return
        try:
            overdue_int = int(vals["overdue_days"])
        except ValueError:
            messagebox.showerror("Error", "Overdue Days must be an integer.")
            return

        conn = get_connection()
        c = conn.cursor()
        try:
            c.execute('''
                INSERT INTO returns (return_id, reservation_id, car_id, return_date, condition, overdue_days)
                VALUES (?, ?, ?, ?, ?, ?)
            ''', (vals["return_id"], vals["reservation_id"], vals["car_id"],
                  vals["return_date"], vals["condition"], overdue_int))
            conn.commit()
        except sqlite3.IntegrityError as e:
            messagebox.showerror("Error", f"Failed to add return: {e}")
            conn.rollback()
            conn.close()
            return
        conn.close()
        messagebox.showinfo("Success", "Return record added successfully!")
        self.clear_entries()
        self.load_returns()

    def update_return(self):
        vals = { key: self.entries[key].get().strip() for key in self.entries }
        if any(v == "" for v in vals.values()):
            messagebox.showerror("Error", "Please fill in all fields.")
            return
        try:
            overdue_int = int(vals["overdue_days"])
        except ValueError:
            messagebox.showerror("Error", "Overdue Days must be an integer.")
            return

        conn = get_connection()
        c = conn.cursor()
        c.execute('''
            UPDATE returns
            SET reservation_id = ?, car_id = ?, return_date = ?, condition = ?, overdue_days = ?
            WHERE return_id = ?
        ''', (vals["reservation_id"], vals["car_id"], vals["return_date"],
              vals["condition"], overdue_int, vals["return_id"]))
        if c.rowcount == 0:
            messagebox.showwarning("Warning", "No record found with given Return ID.")
        else:
            conn.commit()
            messagebox.showinfo("Updated", "Return record updated successfully!")
        conn.close()
        self.clear_entries()
        self.load_returns()

    def delete_return(self):
        return_id = self.entries["return_id"].get().strip()
        if not return_id:
            messagebox.showwarning("Warning", "Please enter the Return ID to delete.")
            return

        conn = get_connection()
        c = conn.cursor()
        c.execute("DELETE FROM returns WHERE return_id = ?", (return_id,))
        if c.rowcount == 0:
            messagebox.showwarning("Warning", f"No return record found with ID: {return_id}")
        else:
            conn.commit()
            messagebox.showinfo("Deleted", f"Return record {return_id} deleted successfully!")
        conn.close()
        self.clear_entries()
        self.load_returns()

    def clear_entries(self):
        for entry in self.entries.values():
            entry.delete(0, tk.END)
