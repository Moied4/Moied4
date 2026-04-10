import tkinter as tk
from tkinter import ttk, messagebox
import os


class PaymentWindow:
    def __init__(self, root, go_back_callback):
        """
        Window for managing payments (CRUD on data/payments.txt)
        """
        self.root = root
        self.go_back_callback = go_back_callback
        self.file_path = "data/payments.txt"

        self.create_widgets()
        self.load_payments()

    def create_widgets(self):
        title = tk.Label(
            self.root,
            text="Payment Management",
            font=("Arial", 18, "bold"),
            fg="#003366"
        )
        title.pack(pady=20)

        form_frame = tk.Frame(self.root)
        form_frame.pack(pady=10)

        # Labels and Entry Fields
        labels = ["Payment ID", "Reservation ID", "Client ID", "Amount", "Date (YYYY-MM-DD)", "Status"]
        self.entries = {}

        for i, label_text in enumerate(labels):
            lbl = tk.Label(form_frame, text=label_text, font=("Arial", 11))
            lbl.grid(row=i, column=0, padx=5, pady=5, sticky="e")

            entry = tk.Entry(form_frame, width=30)
            entry.grid(row=i, column=1, padx=5, pady=5)
            self.entries[label_text] = entry

        # Buttons
        btn_frame = tk.Frame(self.root)
        btn_frame.pack(pady=10)

        add_btn = ttk.Button(btn_frame, text="Add Payment", command=self.add_payment)
        add_btn.grid(row=0, column=0, padx=5)

        update_btn = ttk.Button(btn_frame, text="Update Payment", command=self.update_payment)
        update_btn.grid(row=0, column=1, padx=5)

        delete_btn = ttk.Button(btn_frame, text="Delete Payment", command=self.delete_payment)
        delete_btn.grid(row=0, column=2, padx=5)

        back_btn = ttk.Button(btn_frame, text="Back", command=self.go_back_callback)
        back_btn.grid(row=0, column=3, padx=5)

        # Payment List
        self.table = ttk.Treeview(self.root, columns=("pid", "rid", "cid", "amt", "date", "status"), show="headings", height=8)
        for col in self.table["columns"]:
            self.table.heading(col, text=col.title())
            self.table.column(col, width=100)
        self.table.pack(pady=15)

    # ---------- CRUD Operations ----------

    def add_payment(self):
        values = [entry.get().strip() for entry in self.entries.values()]
        if "" in values:
            messagebox.showerror("Error", "Please fill in all fields.")
            return

        line = "|".join(values) + "\n"

        os.makedirs("data", exist_ok=True)
        with open(self.file_path, "a") as f:
            f.write(line)

        messagebox.showinfo("Success", "Payment added successfully!")
        self.load_payments()
        self.clear_entries()

    def load_payments(self):
        """Read and display all payments."""
        self.table.delete(*self.table.get_children())

        if not os.path.exists(self.file_path):
            return

        with open(self.file_path, "r") as f:
            for line in f:
                parts = line.strip().split("|")
                if len(parts) == 6:
                    self.table.insert("", tk.END, values=parts)

    def update_payment(self):
        """Update selected payment record."""
        selected = self.table.selection()
        if not selected:
            messagebox.showwarning("Warning", "Please select a record to update.")
            return

        updated_values = [entry.get().strip() for entry in self.entries.values()]
        if "" in updated_values:
            messagebox.showerror("Error", "Please fill in all fields.")
            return

        old_values = self.table.item(selected[0], "values")

        with open(self.file_path, "r") as f:
            lines = f.readlines()

        with open(self.file_path, "w") as f:
            for line in lines:
                if line.strip().split("|")[0] == old_values[0]:
                    f.write("|".join(updated_values) + "\n")
                else:
                    f.write(line)

        messagebox.showinfo("Updated", "Payment updated successfully!")
        self.load_payments()
        self.clear_entries()

    def delete_payment(self):
        """Delete selected payment record."""
        selected = self.table.selection()
        if not selected:
            messagebox.showwarning("Warning", "Please select a record to delete.")
            return

        values = self.table.item(selected[0], "values")
        pid = values[0]

        with open(self.file_path, "r") as f:
            lines = f.readlines()

        with open(self.file_path, "w") as f:
            for line in lines:
                if not line.startswith(pid + "|"):
                    f.write(line)

        messagebox.showinfo("Deleted", f"Payment {pid} deleted successfully!")
        self.load_payments()

    def clear_entries(self):
        for entry in self.entries.values():
            entry.delete(0, tk.END)
