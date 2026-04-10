import sqlite3

conn = sqlite3.connect('car_rental.db')
c = conn.cursor()

c.execute("SELECT * FROM cars")
rows = c.fetchall()
if rows:
    for row in rows:
        print(row)
else:
    print("No cars found!")

conn.close()



import sqlite3

conn = sqlite3.connect('car_rental.db')
c = conn.cursor()

# Try this exact query, change 'Camry' to any model you have
search_term = '%Camry%'

c.execute('''
    SELECT car_id, type, model, location, rental_rate FROM cars
    WHERE LOWER(model) LIKE LOWER(?)
''', (search_term,))

results = c.fetchall()
for r in results:
    print(r)

conn.close()
