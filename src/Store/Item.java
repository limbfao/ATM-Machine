package Store;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.ZonedDateTime;

public class Item {
    public String name;
    public int price;
    public String color;
    public String originalOwner;
    public String timeBought;


    // Cool time formatting stuff I found on the internet.
    ZoneId zone = ZoneId.of("-05:00");
    ZonedDateTime mrGrabber = ZonedDateTime.now(zone);
    LocalDate anotherSummerDay = LocalDate.now();

    LocalTime anotherTime = LocalTime.of(mrGrabber.getHour(), mrGrabber.getMinute(), mrGrabber.getSecond());
    ZonedDateTime zonedDateTime = ZonedDateTime.of(anotherSummerDay, anotherTime, zone);

    /**
     *
     * @param name Item Name
     * @param price Item Price
     * @param originalOwner Original owner who buys the item in the store
     */
    Item(String name, int price, String originalOwner) {
        this.name = name;
        this.price = price;
        this.originalOwner = originalOwner;
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zonedDateTime);
        timeBought = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(zonedDateTime);
    }

    public void customizeColor(String color) {
        this.color = color;
    }

}
