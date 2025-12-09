
from PyQt6.QtWidgets import (
    QApplication, QWidget, QGridLayout, QVBoxLayout, QLabel,
    QLineEdit, QTextEdit, QPushButton
)
import sys
from employee_template import Conversion

class ConversionGUI(QWidget):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Currency Conversion - CEGEP Vanier College")
        self.resize(500, 400)

        # --- Layouts ---
        self.grid = QGridLayout()
        self.vbox = QVBoxLayout()

        # --- Widgets for grid layout ---
        self.lblCan = QLabel("Canadian Amount (CAN$):")
        self.txtCan = QLineEdit()

        self.lblUS = QLabel("US Amount ($):")
        self.txtUS = QLineEdit()
        self.txtUS.setReadOnly(True)

        self.lblEuro = QLabel("Euro Amount (€):")
        self.txtEuro = QLineEdit()
        self.txtEuro.setReadOnly(True)

        self.lblYen = QLabel("Japanese Yen (¥):")
        self.txtYen = QLineEdit()
        self.txtYen.setReadOnly(True)

        self.btnCalc = QPushButton("Calculate")
        self.btnReset = QPushButton("Reset")

        # --- Add widgets to grid layout (5 rows x 2 cols) ---
        self.grid.addWidget(self.lblCan, 0, 0)
        self.grid.addWidget(self.txtCan, 0, 1)
        self.grid.addWidget(self.lblUS, 1, 0)
        self.grid.addWidget(self.txtUS, 1, 1)
        self.grid.addWidget(self.lblEuro, 2, 0)
        self.grid.addWidget(self.txtEuro, 2, 1)
        self.grid.addWidget(self.lblYen, 3, 0)
        self.grid.addWidget(self.txtYen, 3, 1)
        self.grid.addWidget(self.btnCalc, 4, 0)
        self.grid.addWidget(self.btnReset, 4, 1)

        # --- TextEdit and “All Amounts” button (VBox layout) ---
        self.txtDisplay = QTextEdit()
        self.btnAll = QPushButton("All Amounts")

        self.vbox.addLayout(self.grid)
        self.vbox.addWidget(self.txtDisplay)
        self.vbox.addWidget(self.btnAll)

        self.setLayout(self.vbox)

        # --- Connections ---
        self.btnCalc.clicked.connect(self.calculateAmounts)
        self.btnReset.clicked.connect(self.resetFields)
        self.btnAll.clicked.connect(self.displayAllAmounts)

        # --- Instance of Conversion class ---
        self.myConversion = Conversion()

    def calculateAmounts(self):
        can_value = self.txtCan.text()
        self.myConversion.setCan_Cur(can_value)

        us_val = self.myConversion.CalculateConversiontoUS()
        eur_val = self.myConversion.CalculateConversiontoEURO()
        yen_val = self.myConversion.CalculateConversiontoYEN()

        # Display values
        self.txtUS.setText(f"{us_val:.2f}")
        self.txtEuro.setText(f"{eur_val:.2f}")
        self.txtYen.setText(f"{yen_val:.2f}")

        # Show in TextEdit
        self.txtDisplay.setText(self.myConversion.displayAll())

    def resetFields(self):
        self.txtCan.clear()
        self.txtUS.clear()
        self.txtEuro.clear()
        self.txtYen.clear()
        self.txtDisplay.clear()

    def displayAllAmounts(self):
        # Create three conversion objects
        Bob_Conversion = Conversion(1000)
        Ana_Conversion = Conversion(2200)
        Sarah_Conversion = Conversion(4000)

        # Display results in TextEdit
        output = ("--- All Conversions ---\n\n"
                  f"Bob_Conversion:\n{Bob_Conversion.displayAll()}\n"
                  f"Ana_Conversion:\n{Ana_Conversion.displayAll()}\n"
                  f"Sarah_Conversion:\n{Sarah_Conversion.displayAll()}\n")

        self.txtDisplay.setText(output)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ConversionGUI()
    window.show()
    sys.exit(app.exec())
