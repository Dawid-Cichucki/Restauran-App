package pl.connectis.restaurant.RestaurantApp.validation;

public class ValidationClass {
    public static boolean isNullOrEmpty(String value) {
        return  value == null || value.isEmpty();
    }

    public static boolean isNull(Object object) {
        return object == null;
    }
}
