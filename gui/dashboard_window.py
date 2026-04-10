import tkinter as tk
from tkinter import ttk, messagebox
import sqlite3
from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from database import get_connection  # database module

class DashboardWindow:
    def __init__(self, root, go_back_callback):
        self.root = root
        self.go_back_callback = go_back_callback
        self.root.title("Business Performance Dashboard")
        self.build_ui()
        self.load_metrics()

    def build_ui(self):
        # Header
        header = tk.Label(self.root, text="Car Rental Performance Dashboard",
                          font=("Arial", 20, "bold"), fg="#003366")
        header.pack(pady=10)

        # Frame for metric cards
        self.cards_frame = tk.Frame(self.root)
        self.cards_frame.pack(fill=tk.X, padx=20)

        self.lbl_total_rev = tk.Label(self.cards_frame, text="Total Revenue\n$0.00",
                                      font=("Arial", 14), bg="#e8f4ff", width=20, height=3,
                                      relief=tk.RIDGE, bd=2)
        self.lbl_total_rev.grid(row=0, column=0, padx=10, pady=5)

        self.lbl_res_count = tk.Label(self.cards_frame, text="Total Reservations\n0",
                                      font=("Arial", 14), bg="#e8f4ff", width=20, height=3,
                                      relief=tk.RIDGE, bd=2)
        self.lbl_res_count.grid(row=0, column=1, padx=10, pady=5)

        self.lbl_util_rate = tk.Label(self.cards_frame, text="Utilisation Rate\n0 %",
                                      font=("Arial", 14), bg="#e8f4ff", width=20, height=3,
                                      relief=tk.RIDGE, bd=2)
        self.lbl_util_rate.grid(row=0, column=2, padx=10, pady=5)

        # Charts frame
        self.charts_frame = tk.Frame(self.root)
        self.charts_frame.pack(fill=tk.BOTH, expand=True, padx=20, pady=10)

        # Revenue Trend Chart
        self.fig_rev = Figure(figsize=(4,2), dpi=100)
        self.ax_rev = self.fig_rev.add_subplot(111)
        self.ax_rev.set_title("Revenue Trend (Last 12 months)")
        self.ax_rev.set_xlabel("Month")
        self.ax_rev.set_ylabel("Revenue")
        self.canvas_rev = FigureCanvasTkAgg(self.fig_rev, master=self.charts_frame)
        self.canvas_rev.get_tk_widget().grid(row=0, column=0, padx=10, pady=10)

        # Payment Status Pie Chart
        self.fig_pay = Figure(figsize=(4,2), dpi=100)
        self.ax_pay = self.fig_pay.add_subplot(111)
        self.ax_pay.set_title("Payment Status Breakdown")
        self.canvas_pay = FigureCanvasTkAgg(self.fig_pay, master=self.charts_frame)
        self.canvas_pay.get_tk_widget().grid(row=0, column=1, padx=10, pady=10)

        # Back button
        back_btn = ttk.Button(self.root, text="Back to Dashboard Menu", command=self.go_back_callback)
        back_btn.pack(pady=10)

    def load_metrics(self):
        try:
            conn = get_connection()
            c = conn.cursor()

            # Total revenue
            c.execute("SELECT SUM(total_cost) FROM reservations")
            total_rev = c.fetchone()[0] or 0
            self.lbl_total_rev.config(text=f"Total Revenue\n${total_rev:.2f}")

            # Total reservations count
            c.execute("SELECT COUNT(*) FROM reservations")
            count_res = c.fetchone()[0] or 0
            self.lbl_res_count.config(text=f"Total Reservations\n{count_res}")

            # Utilisation rate (simple calculation: total days reserved ÷ (cars × days) for last month)
            # This is a rough estimate
            c.execute("SELECT SUM(julianday(end_date) - julianday(start_date) + 1) FROM reservations")
            total_days_reserved = c.fetchone()[0] or 0
            c.execute("SELECT COUNT(*) FROM cars")
            total_cars = c.fetchone()[0] or 0
            # assume last 30 days
            possible_days = total_cars * 30 if total_cars else 1
            util_rate = (total_days_reserved / possible_days) * 100
            self.lbl_util_rate.config(text=f"Utilisation Rate\n{util_rate:.1f}%")

            # Revenue trend: last 12 months
            c.execute("""
                SELECT strftime('%Y-%m', start_date) as month, SUM(total_cost)
                FROM reservations
                GROUP BY month
                ORDER BY month DESC
                LIMIT 12
            """)
            rows = c.fetchall()
            months = [r[0] for r in rows][::-1]
            revenues = [r[1] for r in rows][::-1]
            self.ax_rev.clear()
            self.ax_rev.plot(months, revenues, marker='o')
            self.ax_rev.set_xticklabels(months, rotation=45, ha='right')
            self.fig_rev.tight_layout()
            self.canvas_rev.draw()

            # Payment status breakdown
            c.execute("""
                SELECT payment_status, COUNT(*) FROM reservations
                GROUP BY payment_status
            """)
            rows2 = c.fetchall()
            labels = [r[0] for r in rows2]
            counts = [r[1] for r in rows2]
            self.ax_pay.clear()
            self.ax_pay.pie(counts, labels=labels, autopct='%1.1f%%')
            self.fig_pay.tight_layout()
            self.canvas_pay.draw()

            conn.close()
        except Exception as e:
            messagebox.showerror("Error", f"Dashboard metrics load failed:\n{e}")

