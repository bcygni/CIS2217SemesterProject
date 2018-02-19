import java.util.ArrayList;

//Ryan Betts
//February 11, 2018
//The Utilities class is a new class that contains Generic methods for removing duplicates from an ArrayList
//of any type of object and a generic linear search method through an array of any kind of object
//The main method at the bottom of this class tests many different uses for the new generic methods


public class Utilities {


    //Returns a new ArrayList with duplicates removed
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
        ArrayList<E> newList = new ArrayList<>();

        //Loop through each item in the original list
        for(E item : list){
            //if the new list does not already contain  original list item, add it to the newlist
            if(!newList.contains(item)){
                newList.add(item);
            }
        }
        return newList;
    }


    //Returns the index of an object in an array or -1 if not found
    public static <E extends Comparable<E>>
        int linearSearch(E[] list, E key){
        for(int index = 0; index < list.length; index++ ){

            try {
                if (key.equals(list[index])) {
                    return index;
                }
            }catch (NullPointerException n) {
                    if(list[index] == null)
                        return index;
                }
        }
        return -1;
    }


    //Prints out all items in an ArrayList
    public static <T> void printList(ArrayList<T> list){
        for(T item : list)
            System.out.println(item);
    }

    //Returns a deep copy of an ArrayList
    public static <T> ArrayList<T> copyList(ArrayList<T> list){

        ArrayList<T> copiedList = new ArrayList<>();
        copiedList.addAll(list);
        return copiedList;

    }


    //Method for testing removeDuplicates
    private static <T> void testRemoveDups(ArrayList<T> list){

        ArrayList<T> removedDupsList = removeDuplicates(list);
        System.out.println("Original List: ");
        printList(list);
        System.out.println("\nRemoved Duplicates List: ");
        printList(removedDupsList);

    }

    //Method for testing linearSearch
    private static <T extends Comparable<T>> void testLinearSearch(T[] list, T key){

        int index = linearSearch(list, key);
        if(index >= 0){
            System.out.println("Found " + key + " at " + "index: " + index);
        }
        else{
            System.out.println(key + " was not found in array.");
        }

    }





    public static void main(String[] args) {

        System.out.println("\n--Testing Generic method to remove duplicates from ArrayList------------------------");

        FacebookUser friend1 = new FacebookUser("Kate", "kate");
        FacebookUser friend2 = new FacebookUser("George", "george", "5678");
        FacebookUser friend3 = new FacebookUser("george", "george", "5678");
        FacebookUser friend4 = new FacebookUser("George", "a");
        FacebookUser friend45 = new FacebookUser("NextTwoLinesAreUsers with \"\" and \" \" for names", "");
        FacebookUser friend5 = new FacebookUser("", "");
        FacebookUser friend6 = new FacebookUser(" ", " ", "5678");
        FacebookUser friend7 = new FacebookUser("Kate", "a", "564378");
        FacebookUser friend8 = new FacebookUser(" Kate ", "b", "564378");
        FacebookUser friend9 = new FacebookUser("Kate1", "c", "564378");
        FacebookUser friend10 = new FacebookUser("kate", "d", "564378");


        System.out.println("\n\n----Test with FacebookUser objects (empty list)...................................");
        ArrayList<FacebookUser> friends = new ArrayList<FacebookUser>();
        testRemoveDups(friends);

        System.out.println("\n\n----Test with FacebookUser objects with no duplicates................................");
        friends.add(friend7);
        friends.add(friend8);
        friends.add(friend9);
        friends.add(friend10);
        testRemoveDups(friends);

        System.out.println("\n\n----Test with FacebookUser objects WITH duplicates)...................................");
        friends.add(friend1);
        friends.add(friend2);
        friends.add(friend3);
        friends.add(friend4);   //same username as friend2; should be removed
        friends.add(friend45);
        friends.add(friend5);
        friends.add(friend6);
        friends.add(friend7);   //same username as friend1; should be removed
        friends.add(friend8);   //duplicated object; should be removed
        friends.add(friend9);   //duplicated object; should be removed
        friends.add(friend10);  //duplicated object; should be removed

        testRemoveDups(friends);

        System.out.println("\n\n----Test with Integer objects...................................");
        ArrayList<Integer> integerArray = new ArrayList<>();
        integerArray.add(10);
        integerArray.add(15);
        integerArray.add(null);
        integerArray.add(0);
        integerArray.add(0);
        integerArray.add(-1);
        integerArray.add(1);
        integerArray.add(null);
        integerArray.add(-1);
        testRemoveDups(integerArray);

        System.out.println("\n\n----Test with an ArrayList of ArrayList objects...................................");

        ArrayList<Double> arr1 = new ArrayList<>();
        arr1.add(4.405);
        arr1.add(5.4/3.1);
        arr1.add(5.0/-1.5);

        ArrayList<Double> arr2 = arr1;

        ArrayList<Double> arr1Copy = copyList(arr1);
        arr1Copy.remove(5.4/3.1);
        arr1Copy.add(5.4/3.1);
        arr1Copy.add(0.454545);

        ArrayList<Double> arr3 = new ArrayList<>();
        arr3.add(4.405);
        arr3.add(5.4/3.09);
        arr3.add(-5.0/1.5);
        arr3.add(0.454545);
        arr3.add(null);

        ArrayList<ArrayList<Double>> arrOfArrays = new ArrayList<>();
        arrOfArrays.add(arr1);
        arrOfArrays.add(arr2);
        arrOfArrays.add(arr3);
        arrOfArrays.add(arr1Copy);
        arrOfArrays.add(arr2);
        arr1.add(0.454545);
        arrOfArrays.add(arr1);
        arrOfArrays.add(new ArrayList<>());
        arrOfArrays.add(arr1);
        arrOfArrays.add(arr2);
        arrOfArrays.add(new ArrayList<>());
        arrOfArrays.add(copyList(arr3));

        testRemoveDups(arrOfArrays);


        System.out.println("\n\n--Testing Generic method for linear search through an array------------------------");
        System.out.println("\nTesting with FacebookUser objects");

        FacebookUser[] userArray = {new FacebookUser("Doug", "doug"),
                new FacebookUser("doug", "dog"), new FacebookUser("Kate", "kate"),
        friend1, friend2, friend4, friend5, friend6, friend7, friend8, friend9, friend10};

        for(UserAccount u : userArray)
            System.out.print("[" + u + "], ");
        System.out.println();

        testLinearSearch(userArray, new FacebookUser("Doug", "d"));
        testLinearSearch(userArray, friend1);
        testLinearSearch(userArray, null);
        testLinearSearch(userArray, friend3);
        testLinearSearch(userArray, friend4);




        System.out.println("\nTesting with Double objects");
        Double[] doublesArray = {5.0, 0.0, 4.3, null, -64.3, 42.331, 2.045, -1.1, 0.001, 0.4, 23.3, 4.3, null};

        for(Double dbl : doublesArray)
            System.out.print("[" + dbl + "], ");

        System.out.println();

        testLinearSearch(doublesArray, 0.0);
        testLinearSearch(doublesArray, -64.3);
        testLinearSearch(doublesArray, -4.3);
        testLinearSearch(doublesArray, 4.301);
        testLinearSearch(doublesArray, null);

        System.out.println("\nTesting with String objects");
        String[] strArray = {"00", "000", "0.0", "   ", "-000\n0", "00", "\t", "0", "00", null, "", " "};
        for(String s : strArray)
            System.out.print("[" + s + "], ");
        System.out.println();

        testLinearSearch(strArray, "0");
        testLinearSearch(strArray, "-000-");
        testLinearSearch(strArray, "");
        testLinearSearch(strArray, "\t");
        testLinearSearch(strArray, "\n");
        testLinearSearch(strArray, "dog");
        testLinearSearch(strArray, null);
        testLinearSearch(strArray, "-000\n0");

        System.out.println("\nTesting with empty array");
        Integer[] intArray = {};
        for(Integer i : intArray)
            System.out.print("[" + i + "], ");
        System.out.println();      
        
        testLinearSearch(intArray, null);
        testLinearSearch(intArray, 0);       
        
    }





}

