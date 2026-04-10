import tkinter as tk
from gui.payment_window import PaymentWindow
from gui.return_window import ReturnWindow
from gui.add_car_window import AddCarWindow
from gui.view_clients_window import ViewClientsWindow
from gui.view_reservations_window import ViewReservationsWindow
from gui.dashboard_window import DashboardWindow  # Import the dashboard

class ManagerWindow:
    def __init__(self, root,
                 payment_callback,
                 return_callback,
                 add_car_callback,
                 view_clients_callback,
                 view_reservations_callback,
                 logout_callback):
        self.root = root
        self.payment_callback = payment_callback
        self.return_callback = return_callback
        self.add_car_callback = add_car_callback
        self.view_clients_callback = view_clients_callback
        self.view_reservations_callback = view_reservations_callback
        self.logout_callback = logout_callback

        self.root.title("Manager Dashboard")
        self.build_ui()

    def build_ui(self):
        self.clear_window()

        tk.Label(self.root, text="Manager Dashboard", font=("Arial", 18)).pack(pady=20)

        tk.Button(self.root, text="Process Payments", width=25, command=self.open_payment).pack(pady=5)
        tk.Button(self.root, text="Process Returns", width=25, command=self.open_return).pack(pady=5)
        tk.Button(self.root, text="Add Car", width=25, command=self.open_add_car).pack(pady=5)
        tk.Button(self.root, text="View Clients", width=25, command=self.open_view_clients).pack(pady=5)
        tk.Button(self.root, text="View Reservations", width=25, command=self.open_view_reservations).pack(pady=5)
        tk.Button(self.root, text="View Performance Dashboard", width=25, command=self.open_dashboard).pack(pady=5)
        tk.Button(self.root, text="Logout", width=25, command=self.logout_callback).pack(pady=20)

    # Flow
    def open_payment(self):
        self.clear_window()
        PaymentWindow(self.root, self.build_ui)

    def open_return(self):
        self.clear_window()
        ReturnWindow(self.root, self.build_ui)

    def open_add_car(self):
        self.clear_window()
        AddCarWindow(self.root, self.build_ui)

    def open_view_clients(self):
        self.clear_window()
        ViewClientsWindow(self.root, self.build_ui)

    def open_view_reservations(self):
        self.clear_window()
        ViewReservationsWindow(self.root, self.build_ui)

    # Dashboard opens in a separate Toplevel window
    def open_dashboard(self):
        DashboardWindow(self.root, go_back_callback=None)

    # Utility to clear root window widgets
    def clear_window(self):
        for widget in self.root.winfo_children():
            widget.destroy()
