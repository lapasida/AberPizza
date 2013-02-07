package uk.ac.aber.dcs.cs12420.aberpizza.data;

public enum DiscountType {
	PIZZA{
		public String toString() {
			return "Pizza";
		}
	},
	SMALL_PIZZA{
		public String toString() {
			return "Small Pizza";
		}
	},
	MEDIUM_PIZZA{
		public String toString() {
			return "Medium Pizza";
		}
	},
	LARGE_PIZZA{
		public String toString() {
			return "Large Pizza";
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
