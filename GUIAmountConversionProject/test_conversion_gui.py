import sys
from PyQt6.QtWidgets import QApplication
from gui_conversion import ConversionGUI

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ConversionGUI()
    window.show()
    sys.exit(app.exec())
