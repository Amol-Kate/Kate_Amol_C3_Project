public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }
    // CREATING A NEW METHOD "getItemCost" FOR PART 3 SOLUTION:
    public int getItemCost() {
        return price;
    }

}
