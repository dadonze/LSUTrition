package csc4330.lsutrition;

/**
 * Created by Danny on 11/22/2017.
 */
/*
    FakeDataUtils is a utility class designed for assiting in debugging and providing a safety net in the event
    of a Server or Internet issue during the presentation
 */
public class FakeDataUtils {

    public static String[] generateData(){
        String[] data = new String[25];
        for (int i = 0; i < 10;i++){
            data[i] = "Restaurant " + i;
        }
        return data;
    }
    public static RestaurantMenuItem[] generateFakeMenu()
    {
        RestaurantMenuItem[] data = new RestaurantMenuItem[8];
        data[0] = new RestaurantMenuItem("Big Mac",700);
        data[1] = new RestaurantMenuItem("McDouble",500);
        data[2] = new RestaurantMenuItem("Soda",200);
        data[3] = new RestaurantMenuItem("McFlurry",550);
        data[4] = new RestaurantMenuItem("Fries",350);
        data[5] = new RestaurantMenuItem("Hot'N'Spicy",200);
        data[6] = new RestaurantMenuItem("Chicken Nuggs",500);
        data[7] = new RestaurantMenuItem("Chicken Tenders",400);
        return data;

    }
}