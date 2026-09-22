package enums;

/**
 * Заголовки страниц сайта. Храним в enum, чтобы не хардкодить строки.
 */
public enum TitleNaming {
    LOGIN("Swag Labs"),
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT_INFO("Checkout: Your Information"),
    CHECKOUT_OVERVIEW("Checkout: Overview"),
    CHECKOUT_COMPLETE("Checkout: Complete!");

    private final String title;

    TitleNaming(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
