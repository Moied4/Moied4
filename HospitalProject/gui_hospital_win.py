# gui_hospital_win.py

import sys
from PyQt6.QtWidgets import QApplication, QWidget, QLabel, QLineEdit, QPushButton, QVBoxLayout, QFormLayout
from hospital_template import Hospital

class HospitalGUI(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Hospital Project")
        self.setup_ui()

    def setup_ui(self):
        # Form layout for input fields
        form_layout = QFormLayout()

        self.hospId_input = QLineEdit()
        self.hospName_input = QLineEdit()
        self.hospNumSurgery_input = QLineEdit()
        self.costSurgery_input = QLineEdit()
        self.surgeryDiscount_input = QLineEdit()
        self.hospRevenue_output = QLineEdit()
        self.hospRevenue_output.setReadOnly(True)  # Output field

        # Add fields to form
        form_layout.addRow("Hospital ID:", self.hospId_input)
        form_layout.addRow("Hospital Name:", self.hospName_input)
        form_layout.addRow("Number of Surgeries:", self.hospNumSurgery_input)
        form_layout.addRow("Cost per Surgery:", self.costSurgery_input)
        form_layout.addRow("Surgery Discount (%):", self.surgeryDiscount_input)
        form_layout.addRow("Hospital Revenue:", self.hospRevenue_output)

        # Push button
        self.calc_button = QPushButton("Calculate Hospital Revenue")
        self.calc_button.clicked.connect(self.calculate_revenue)

        # Layout
        main_layout = QVBoxLayout()
        main_layout.addLayout(form_layout)
        main_layout.addWidget(self.calc_button)

        self.setLayout(main_layout)

    def calculate_revenue(self):
        # Instantiate Hospital object
        hosp1 = Hospital()
        hosp1.set_hospId(self.hospId_input.text())
        hosp1.set_hospName(self.hospName_input.text())
        hosp1.set_hospNumSurgery(int(self.hospNumSurgery_input.text()))
        hosp1.set_costSurgery(float(self.costSurgery_input.text()))
        hosp1.set_surgeryDiscount(float(self.surgeryDiscount_input.text()))

        # Calculate revenue
        revenue = hosp1.CalculateHospRevenue()
        self.hospRevenue_output.setText(f"{revenue:.2f}")

        # Save hospital data to file
        with open("Hospital.txt", "w") as file:
            file.write(f"Hospital ID: {hosp1.get_hospId()}\n")
            file.write(f"Hospital Name: {hosp1.get_hospName()}\n")
            file.write(f"Number of Surgeries: {hosp1.get_hospNumSurgery()}\n")
            file.write(f"Cost per Surgery: {hosp1.get_costSurgery():.2f}\n")
            file.write(f"Surgery Discount (%): {hosp1.get_surgeryDiscount():.2f}\n")
            file.write(f"Total Hospital Revenue: {revenue:.2f}\n")

# Run the application
if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = HospitalGUI()
    window.show()
    sys.exit(app.exec())
