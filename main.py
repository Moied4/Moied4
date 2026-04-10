import tkinter as tk
from gui.register_window import RegisterWindow
from gui.login_window import LoginWindow
from gui.search_window import SearchWindow
from gui.reservation_window import ReservationWindow
from gui.manager_window import ManagerWindow
from gui.payment_window import PaymentWindow
from gui.return_window import ReturnWindow
from gui.add_car_window import AddCarWindow
from gui.view_clients_window import ViewClientsWindow
from gui.view_reservations_window import ViewReservationsWindow
import database

class CarRentalApp:
    def __init__(self, root):
        self.root = root
        self.client_id = None
        self.manager_id = None
        database.create_tables()  # ensure DB exists
        self.show_login()

    # ---------- Client Flow ----------
    def show_login(self):
        self.clear_window()
        LoginWindow(self.root, self.after_client_login, self.show_register, self.show_manager_login)

    def show_register(self):
        self.clear_window()
        RegisterWindow(self.root, self.show_login)

    def after_client_login(self, client_id):
        self.client_id = client_id
        self.show_search()

    def show_search(self):
        self.clear_window()
        SearchWindow(self.root, self.client_id, self.after_car_selected)

    def after_car_selected(self, car_id, start_date, end_date):
        self.clear_window()
        ReservationWindow(
            self.root,
            self.client_id,
            car_id,
            start_date,
            end_date,
            self.after_reservation
        )

    def after_reservation(self):
        self.show_search()

    # ---------- Manager Flow ----------
    def show_manager_login(self):
        self.clear_window()
        ManagerWindow(
            self.root,
            self.show_payment_window,
            self.show_return_window,
            self.show_add_car_window,
            self.show_view_clients_window,
            self.show_view_reservations_window,
            self.show_login  # logout
        )

    def show_payment_window(self):
        self.clear_window()
        PaymentWindow(self.root, self.show_manager_login)

    def show_return_window(self):
        self.clear_window()
        ReturnWindow(self.root, self.show_manager_login)

    def show_add_car_window(self):
        self.clear_window()
        AddCarWindow(self.root, self.show_manager_login)

    def show_view_clients_window(self):
        self.clear_window()
        ViewClientsWindow(self.root, self.show_manager_login)

    def show_view_reservations_window(self):
        self.clear_window()
        ViewReservationsWindow(self.root, self.show_manager_login)

    # ---------- Utility ----------
    def clear_window(self):
        for widget in self.root.winfo_children():
            widget.destroy()

if __name__ == "__main__":
    root = tk.Tk()
    root.geometry("800x600")
    root.title("Car Rental System - Phase 3")
    app = CarRentalApp(root)
    root.mainloop()
