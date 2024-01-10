import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Importing Mockito library:
import org.mockito.Mockito;

class RestaurantTest {
    Restaurant restaurant;

    //ADDING "setUpRestaurantDetails()" METHOD BELOW:

    //ADDING "@BeforeEach" ANNOTATION FOR REFACTORING THE CODES.

    @BeforeEach
    public void setUpRestaurantDetails(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //USING MOCKING FOR LOCAL TIME BELOW
        LocalTime mockOpenTime = LocalTime.parse("13:00:00");
        Restaurant mockRestaurant = Mockito.spy(restaurant);
        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(mockOpenTime);
        assertTrue(mockRestaurant.isRestaurantOpen());

        //ADDED NEW CODES ABOVE - Solution
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //USING MOCKING FOR LOCAL TIME BELOW
        LocalTime mockCloseTime = LocalTime.parse("23:00:00");
        Restaurant mockRestaurant = Mockito.spy(restaurant);
        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(mockCloseTime);
        assertFalse(mockRestaurant.isRestaurantOpen());

        //ADDED NEW CODES ABOVE - Solution
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    // CODE FOR PART 3:
    //>>>>>>>>>>>>>>>>>>>>>>>>>TOTAL ORDER COST<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // FAILING TEST CASES FOR TOTAL ORDER COST FEATURE
    @Test
    public void selected_items_from_the_menu_should_return_order_cost_greater_than_zero() {
        int cost;
        List<String> itemsSelected = Arrays.asList("Sweet corn soup", "Vegetable lasagne"); //TWO ITEMS SELECTED FROM MENU
        cost = restaurant.getCost(itemsSelected);
        assertEquals(388, cost);
    }

    @Test
    public void items_not_selected_should_return_order_cost_equal_to_zero() {
        int cost;
        List<String> itemsSelected = Arrays.asList(); //No item is selected in this test case
        cost = restaurant.getCost(itemsSelected);
        assertEquals(0, cost);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER COST>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // FOLLOWING CODES ARE REFACTORED USING @BeforeEach ANNOTATION:

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}