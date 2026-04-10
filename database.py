import sqlite3
from datetime import datetime

DB_NAME = 'car_rental.db'

def get_connection():
    return sqlite3.connect(DB_NAME)

# ---------- Table Creation ----------
def create_tables():
    conn = get_connection()
    c = conn.cursor()

    # Clients Table
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

    # Cars Table
    c.execute('''
        CREATE TABLE IF NOT EXISTS cars (
            car_id INTEGER PRIMARY KEY AUTOINCREMENT,
            type TEXT,
            model TEXT,
            location TEXT,
            rental_rate REAL
        )
    ''')

    # Reservations Table
    c.execute('''
        CREATE TABLE IF NOT EXISTS reservations (
            reservation_id TEXT PRIMARY KEY,
            client_id TEXT,
            car_id INTEGER,
            start_date TEXT,
            end_date TEXT,
            payment_status TEXT,
            return_status TEXT,
            total_cost REAL,
            FOREIGN KEY(client_id) REFERENCES clients(client_id),
            FOREIGN KEY(car_id) REFERENCES cars(car_id)
        )
    ''')

    # Managers Table
    c.execute('''
        CREATE TABLE IF NOT EXISTS managers (
            manager_id TEXT PRIMARY KEY,
            name TEXT,
            password TEXT
        )
    ''')

    conn.commit()
    conn.close()

# ---------- CRUD Operations ----------

# ----- Clients -----
def create_client(client_id, name, email, contact_number, license_number, address):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        INSERT INTO clients (client_id, name, email, contact_number, license_number, address)
        VALUES (?, ?, ?, ?, ?, ?)
    ''', (client_id, name, email, contact_number, license_number, address))
    conn.commit()
    conn.close()

def get_clients():
    conn = get_connection()
    c = conn.cursor()
    c.execute("SELECT * FROM clients")
    rows = c.fetchall()
    conn.close()
    return rows

def update_client(client_id, name, email, contact_number, license_number, address):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        UPDATE clients
        SET name=?, email=?, contact_number=?, license_number=?, address=?
        WHERE client_id=?
    ''', (name, email, contact_number, license_number, address, client_id))
    conn.commit()
    conn.close()

def delete_client(client_id):
    conn = get_connection()
    c = conn.cursor()
    c.execute("DELETE FROM clients WHERE client_id=?", (client_id,))
    conn.commit()
    conn.close()

# ----- Cars -----
def create_car(type_, model, location, rental_rate):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        INSERT INTO cars (type, model, location, rental_rate)
        VALUES (?, ?, ?, ?)
    ''', (type_, model, location, rental_rate))
    conn.commit()
    conn.close()

def get_cars():
    conn = get_connection()
    c = conn.cursor()
    c.execute("SELECT * FROM cars")
    rows = c.fetchall()
    conn.close()
    return rows

def update_car(car_id, type_, model, location, rental_rate):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        UPDATE cars
        SET type=?, model=?, location=?, rental_rate=?
        WHERE car_id=?
    ''', (type_, model, location, rental_rate, car_id))
    conn.commit()
    conn.close()

def delete_car(car_id):
    conn = get_connection()
    c = conn.cursor()
    # Check reservations before deleting
    c.execute("SELECT COUNT(*) FROM reservations WHERE car_id=?", (car_id,))
    if c.fetchone()[0] > 0:
        conn.close()
        return False  # Can't delete car with reservations
    c.execute("DELETE FROM cars WHERE car_id=?", (car_id,))
    conn.commit()
    conn.close()
    return True

# ----- Reservations -----
def create_reservation(reservation_id, client_id, car_id, start_date, end_date, payment_status, return_status, total_cost):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        INSERT INTO reservations (reservation_id, client_id, car_id, start_date, end_date, payment_status, return_status, total_cost)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    ''', (reservation_id, client_id, car_id, start_date, end_date, payment_status, return_status, total_cost))
    conn.commit()
    conn.close()

def get_reservations():
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        SELECT r.reservation_id, c.name, car.model, r.start_date, r.end_date, r.payment_status, r.return_status, r.total_cost
        FROM reservations r
        JOIN clients c ON r.client_id=c.client_id
        JOIN cars car ON r.car_id=car.car_id
    ''')
    rows = c.fetchall()
    conn.close()
    return rows

def update_reservation(reservation_id, client_id, car_id, start_date, end_date, payment_status, return_status, total_cost):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        UPDATE reservations
        SET client_id=?, car_id=?, start_date=?, end_date=?, payment_status=?, return_status=?, total_cost=?
        WHERE reservation_id=?
    ''', (client_id, car_id, start_date, end_date, payment_status, return_status, total_cost, reservation_id))
    conn.commit()
    conn.close()

def delete_reservation(reservation_id):
    conn = get_connection()
    c = conn.cursor()
    c.execute("DELETE FROM reservations WHERE reservation_id=?", (reservation_id,))
    conn.commit()
    conn.close()

# ----- Managers -----
def create_manager(manager_id, name, password):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        INSERT INTO managers (manager_id, name, password)
        VALUES (?, ?, ?)
    ''', (manager_id, name, password))
    conn.commit()
    conn.close()

def get_managers():
    conn = get_connection()
    c = conn.cursor()
    c.execute("SELECT * FROM managers")
    rows = c.fetchall()
    conn.close()
    return rows

def update_manager(manager_id, name, password):
    conn = get_connection()
    c = conn.cursor()
    c.execute('''
        UPDATE managers
        SET name=?, password=?
        WHERE manager_id=?
    ''', (name, password, manager_id))
    conn.commit()
    conn.close()

def delete_manager(manager_id):
    conn = get_connection()
    c = conn.cursor()
    c.execute("DELETE FROM managers WHERE manager_id=?", (manager_id,))
    conn.commit()
    conn.close()

# ---------- Sample Data ----------
def insert_sample_clients():
    conn = get_connection()
    c = conn.cursor()
    sample_clients = [
        ('C001', 'Moied Ahmed', 'moied@example.com', '1234567890', 'D1234567', '123 Rue Saint-Martin, Laval'),
        ('C002', 'John Carlos', 'john@example.com', '0987654321', 'D7654321', '456 Boulevard Saint-Elzear, Laval'),
    ]
    c.executemany('''
        INSERT OR IGNORE INTO clients (client_id, name, email, contact_number, license_number, address)
        VALUES (?, ?, ?, ?, ?, ?)
    ''', sample_clients)
    conn.commit()
    conn.close()

def insert_sample_cars():
    conn = get_connection()
    c = conn.cursor()
    sample_cars = [
        ('Sedan', 'Toyota Camry', 'Laval', 50.0),
        ('SUV', 'Honda CRV', 'Laval', 65.0),
        ('Compact', 'Ford Fiesta', 'Laval', 40.0),
        ('Convertible', 'Mazda MX-5', 'Laval', 80.0),
        ('SUV', 'Toyota RAV4', 'Montreal', 70.0),
        ('Sedan', 'Honda Accord', 'Montreal', 55.0),
        ('Compact', 'Chevrolet Spark', 'Laval', 35.0),
        ('Convertible', 'BMW Z4', 'Montreal', 90.0),
        ('Pickup', 'Ford F-150', 'Laval', 85.0),
        ('Van', 'Dodge Grand Caravan', 'Montreal', 75.0)
    ]
    c.executemany('''
        INSERT OR IGNORE INTO cars (type, model, location, rental_rate)
        VALUES (?, ?, ?, ?)
    ''', sample_cars)
    conn.commit()
    conn.close()

def insert_sample_managers():
    conn = get_connection()
    c = conn.cursor()
    sample_managers = [
        ('M001', 'Zara', 'zara')
    ]
    c.executemany('''
        INSERT OR IGNORE INTO managers (manager_id, name, password)
        VALUES (?, ?, ?)
    ''', sample_managers)
    conn.commit()
    conn.close()

    import sqlite3

    conn = sqlite3.connect("car_rental.db")
    c = conn.cursor()

    # Add the column that was missing
    try:
        c.execute("ALTER TABLE reservations ADD COLUMN return_status TEXT DEFAULT 'Not Returned'")
        print("Column return_status added successfully.")
    except sqlite3.OperationalError as e:
        # If column already exists, there will be error; ignore it
        print("Could not add column (it may already exist):", e)

    conn.commit()
    conn.close()


if __name__ == '__main__':
    create_tables()
    insert_sample_clients()
    insert_sample_cars()
    insert_sample_managers()
    print("Database setup complete with full CRUD support and sample data.")
