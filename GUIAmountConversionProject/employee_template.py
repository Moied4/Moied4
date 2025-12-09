class Conversion:
    """A class to convert Canadian currency to other currencies."""

    def __init__(self, can_Cur=0.0):
        self.__can_Cur = can_Cur

    # Accessor (Getter)
    def getCan_Cur(self):
        return self.__can_Cur

    # Mutator (Setter)
    def setCan_Cur(self, value):
        try:
            self.__can_Cur = float(value)
        except ValueError:
            self.__can_Cur = 0.0

    # Conversion Methods
    def CalculateConversiontoUS(self):
        return self.__can_Cur * 0.76

    def CalculateConversiontoEURO(self):
        return self.__can_Cur * 0.68

    def CalculateConversiontoYEN(self):
        return self.__can_Cur * 81.73

    # Helper method to show all conversions as a formatted string
    def displayAll(self):
        return (f"CAN$: {self.__can_Cur:.2f}\n"
                f"US$: {self.CalculateConversiontoUS():.2f}\n"
                f"EURO: {self.CalculateConversiontoEURO():.2f}\n"
                f"YEN: {self.CalculateConversiontoYEN():.2f}\n")
