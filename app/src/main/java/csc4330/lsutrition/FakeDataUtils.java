package csc4330.lsutrition;


/**
    FakeDataUtils is a utility class designed for assiting in debugging and providing a safety net in the event
    of a Server or Internet issue during the presentation
 */
public class FakeDataUtils {
    /**
     * generates a fake McDonalds menu used for testing/demonstration
     * @return an array of fake restaurant menu data
     */
        public static String[] generateRestaurantNames(){
            String[] data = new String[25];
            data[0] = "Chik-Fil-A";
            data[1] = "Panda Express";
            data[2] = "Insomnia Cookies";
            data[3] = "Starbucks";
            data[4] = "Mcdonalds";
            data[5] = "Chik-Fil-A";
            data[6] = "Panda Express";
            data[7] = "Insomnia Cookies";
            data[8] = "Starbucks";
            data[9] = "Mcdonalds";
            data[10] = "Chik-Fil-A";
            data[11] = "Panda Express";
            data[12] = "Insomnia Cookies";
            data[13] = "Starbucks";
            data[14] = "Mcdonalds";
            data[15] = "Chik-Fil-A";
            data[16] = "Panda Express";
            data[17] = "Insomnia Cookies";
            data[18] = "Starbucks";
            data[19] = "Mcdonalds";
            data[20] = "Chik-Fil-A";
            data[21] = "Panda Express";
            data[22] = "Insomnia Cookies";
            data[23] = "Starbucks";
            data[24] = "Mcdonalds";
            return data;

        }
    public static RestaurantMenuItem[] generateFakeMenu(String restaurant)
    {
        if(restaurant.equals("Chik-Fil-A")) {
            RestaurantMenuItem[] data = new RestaurantMenuItem[16];
            data[0] = new RestaurantMenuItem("Grilled Market Salad", 330);
            data[1] = new RestaurantMenuItem("Cobb Salad", 510);
            data[2] = new RestaurantMenuItem("Chick-fil-A Nuggets (8)", 260);
            data[3] = new RestaurantMenuItem("Chick-fil-A Nuggets (12)", 390);
            data[4] = new RestaurantMenuItem("Chick-fil-A Chicken Sandwich", 440);
            data[5] = new RestaurantMenuItem("Grilled Chicken Sandwich", 310);
            data[6] = new RestaurantMenuItem("Spicy Chicken Sandwich", 450);
            data[7] = new RestaurantMenuItem("Waffle Potato Fries", 460);
            data[8] = new RestaurantMenuItem("Chocolate Chunk Cookie", 300);
            data[9] = new RestaurantMenuItem("Chick-n-Minis (4)", 370);
            data[10] = new RestaurantMenuItem("Hash Browns", 240);
            data[11] = new RestaurantMenuItem("Chick-fil-A Chicken Biscuit", 440);
            data[12] = new RestaurantMenuItem("Bacon, Egg & Cheese Biscuit", 460);
            data[13] = new RestaurantMenuItem("Sausage, Egg & Cheese Biscuit", 670);
            data[14] = new RestaurantMenuItem("Hand Spun Milkshake (Large)", 620);
            data[15] = new RestaurantMenuItem("Frosted Lemonade (Large)", 330);
            return data;

        }else if (restaurant.equals("Starbucks")){
            RestaurantMenuItem[] data = new RestaurantMenuItem[7];
            data[0] = new RestaurantMenuItem("Cappuccino", 120);
            data[1] = new RestaurantMenuItem("Caffe Mocha", 330);
            data[2] = new RestaurantMenuItem("Coffee", 5);
            data[3] = new RestaurantMenuItem("Chai Tea Latte", 240);
            data[4] = new RestaurantMenuItem("Espresso", 5);
            data[5] = new RestaurantMenuItem("Iced Tea", 80);
            data[6] = new RestaurantMenuItem("Caramel Macchiato (Medium)", 240);
            return data;

        }else if (restaurant.equals("Insomnia Cookies")){
            RestaurantMenuItem[] data = new RestaurantMenuItem[13];
            data[0] = new RestaurantMenuItem("Chocolate Chunk Cookie", 125);
            data[1] = new RestaurantMenuItem("Double Chocolate Chunk Cookie", 250);
            data[2] = new RestaurantMenuItem("Double Chocolate Mint Cookie", 250);
            data[3] = new RestaurantMenuItem("M&M Cookie", 125);
            data[4] = new RestaurantMenuItem("Oatmeal Raisin Cookie", 230);
            data[5] = new RestaurantMenuItem("Peanut Butter Chip Cookie", 280);
            data[6] = new RestaurantMenuItem("White Chocolate Macadamia Cookie", 270);
            data[7] = new RestaurantMenuItem("Snickerdoodle Cookie", 180);
            data[8] = new RestaurantMenuItem("Sugar Cookie", 190);
            data[9] = new RestaurantMenuItem("Deluxe Triple Chocolate Chunk Cookie", 570);
            data[10] = new RestaurantMenuItem("Deluxe Reese's Peanut Butter Cup Cookie", 630);
            data[11] = new RestaurantMenuItem("Deluxe Smores Cookie", 540);
            data[12] = new RestaurantMenuItem("Chocolate Chip Brownie", 500);

            return data;
        }else if (restaurant.equals("Panda Express")){
            RestaurantMenuItem[] data = new RestaurantMenuItem[15];
            data[0] = new RestaurantMenuItem("Beijing Beef", 470);
            data[1] = new RestaurantMenuItem("Black Pepper Chicken", 280);
            data[2] = new RestaurantMenuItem("Broccoli Beef", 150);
            data[3] = new RestaurantMenuItem("Chicken Egg Roll", 200);
            data[4] = new RestaurantMenuItem("Chow Mein", 510);
            data[5] = new RestaurantMenuItem("Cream Cheese Rangoon", 190);
            data[6] = new RestaurantMenuItem("Crispy Shrimp", 130);
            data[7] = new RestaurantMenuItem("Fried Rice", 520);
            data[8] = new RestaurantMenuItem("Honey Sesame Chicken Breast", 380);
            data[9] = new RestaurantMenuItem("Honey Walnut Shrimp", 360);
            data[10] = new RestaurantMenuItem("Kung Pao Chicken", 290);
            data[11] = new RestaurantMenuItem("Mixed Vegetables", 80);
            data[12] = new RestaurantMenuItem("Orange Chicken", 380);
            data[13] = new RestaurantMenuItem("White Steamed Rice", 380);
            return data;

        }else{
            RestaurantMenuItem[] data = new RestaurantMenuItem[8];
            data[0] = new RestaurantMenuItem("Big Mac", 700);
            data[1] = new RestaurantMenuItem("McDouble", 500);
            data[2] = new RestaurantMenuItem("Soda", 200);
            data[3] = new RestaurantMenuItem("McFlurry", 550);
            data[4] = new RestaurantMenuItem("Fries", 350);
            data[5] = new RestaurantMenuItem("Hot'N'Spicy", 200);
            data[6] = new RestaurantMenuItem("Chicken Nuggs", 500);
            data[7] = new RestaurantMenuItem("Chicken Tenders", 400);
            return data;
        }

    }
    public  static String fakeDeals(){
        return "fake";
    }
}
