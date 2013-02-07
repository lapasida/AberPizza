package uk.ac.aber.dcs.cs12420.aberpizza.data;

public enum FoodType {
	PIZZA{
		public String toString() {
			return "Pizza";
		}
	},
	SIDE{
		public String toString() {
			return "Side";
		}
	},
	DRINK{
		public String toString() {
			return "Drink";
		}
	}
}
