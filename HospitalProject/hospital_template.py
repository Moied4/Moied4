# hospital_template.py

class Hospital:
    def __init__(self, hospId="", hospName="", hospNumSurgery=0, costSurgery=0.0, surgeryDiscount=0.0):
        self.__hospId = hospId
        self.__hospName = hospName
        self.__hospNumSurgery = hospNumSurgery
        self.__costSurgery = costSurgery
        self.__surgeryDiscount = surgeryDiscount

    # Accessor (getter) methods
    def get_hospId(self):
        return self.__hospId

    def get_hospName(self):
        return self.__hospName

    def get_hospNumSurgery(self):
        return self.__hospNumSurgery

    def get_costSurgery(self):
        return self.__costSurgery

    def get_surgeryDiscount(self):
        return self.__surgeryDiscount

    # Mutator (setter) methods
    def set_hospId(self, hospId):
        self.__hospId = hospId

    def set_hospName(self, hospName):
        self.__hospName = hospName

    def set_hospNumSurgery(self, hospNumSurgery):
        self.__hospNumSurgery = hospNumSurgery

    def set_costSurgery(self, costSurgery):
        self.__costSurgery = costSurgery

    def set_surgeryDiscount(self, surgeryDiscount):
        self.__surgeryDiscount = surgeryDiscount

    # Method to calculate total hospital discount
    def CalculateHospDiscount(self):
        tot_discount = self.__hospNumSurgery * self.__costSurgery * (self.__surgeryDiscount / 100)
        return tot_discount

    # Method to calculate total hospital revenue after discount
    def CalculateHospRevenue(self):
        total_cost = self.__hospNumSurgery * self.__costSurgery
        revenue_after_discount = total_cost - self.CalculateHospDiscount()
        return revenue_after_discount
