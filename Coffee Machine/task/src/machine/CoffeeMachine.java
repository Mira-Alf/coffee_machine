package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final int WATER_INDEX = 0;
    private static final int MILK_INDEX = 1;
    private static final int COFFEE_INDEX = 2;
    private static final int CUP_INDEX = 3;
    private static final int AMOUNT_INDEX = 4;
    private static final String BUY_ACTION = "buy";
    private static final String FILL_ACTION = "fill";
    private static final String TAKE_ACTION = "take";
    private static final String REMAINING_ACTION = "remaining";
    private static final String EXIT_ACTION = "exit";

    private final Scanner scanner = new Scanner(System.in);

    private int[] ingredientsWithTheMachine = {400, 540, 120, 9, 550 };

    private static final String ORDER_ESPRESSO = "1";
    private final int[] ingredientsNeededForEspresso = {250, 0, 16, 1, 4};
    private static final String ORDER_LATTE = "2";
    private final int[] ingredientsNeededForLatte = {350, 75, 20, 1, 7};
    private static final String ORDER_CAPUCCINO = "3";
    private final int[] ingredientsNeededForCapuccino = {200, 100, 12, 1, 6};
    private static final String ORDER_BACK = "back";

    private static final CoffeeMachine machine = new CoffeeMachine();
    public static CoffeeMachine getCoffeeMachine() {
        return machine;
    }

    private void displayMachineIngredients() {
        System.out.printf("The coffee machine has:%n" +
                            "%d ml of water%n" +
                            "%d ml of milk%n" +
                            "%d g of coffee beans%n" +
                            "%d of disposable cups%n" +
                            "$%d of money%n", ingredientsWithTheMachine[0],
                                ingredientsWithTheMachine[1],
                                ingredientsWithTheMachine[2],
                                ingredientsWithTheMachine[3],
                                ingredientsWithTheMachine[4]);
    }

    private boolean checkIngredients( int[] orderIngredientsArray ) {
        for( int i = 0; i < 4; i++ ) {
            if( this.ingredientsWithTheMachine[i] < orderIngredientsArray[i] ) {
                String ingredientMissing = "";
                switch( i ) {
                    case WATER_INDEX:
                        ingredientMissing = "water";
                        break;
                    case MILK_INDEX:
                        ingredientMissing = "milk";
                        break;
                    case COFFEE_INDEX:
                        ingredientMissing = "coffee";
                        break;
                    case CUP_INDEX:
                        ingredientMissing = "cups";
                        break;
                    default:
                }
                System.out.printf("Sorry, not enough %s%n", ingredientMissing);
                return false;
            }
        }
        System.out.println("I have enough resources, making you a cup of coffee!");
        return true;
    }

    private void processIngredients( int[] orderIngredientsArray ) {
        if( checkIngredients( orderIngredientsArray ) ) {
            for (int i = 0; i < 4; i++) {
                this.ingredientsWithTheMachine[i] -= orderIngredientsArray[i];
            }
            this.ingredientsWithTheMachine[4] += orderIngredientsArray[4];
        }
    }

    public void processBuy() {
        System.out.printf("%nWhat do you want to buy? %s - espresso, %s - latte, " +
                   "%s - capuccino, %s - to main menu:%n", ORDER_ESPRESSO, ORDER_LATTE,
                    ORDER_CAPUCCINO, ORDER_BACK );
        String orderChoice = scanner.next();
        switch(orderChoice) {
            case ORDER_ESPRESSO:
                processIngredients(this.ingredientsNeededForEspresso);
                break;
            case ORDER_LATTE:
                processIngredients(this.ingredientsNeededForLatte);
                break;
            case ORDER_CAPUCCINO:
                processIngredients(this.ingredientsNeededForCapuccino);
                break;
            default:
        }
    }

    public void processFill() {
        System.out.println("Write how many ml of water do you want to add:" );
        this.ingredientsWithTheMachine[WATER_INDEX] += scanner.nextInt();
        System.out.println( "Write how many ml of milk do you want to add:" );
        this.ingredientsWithTheMachine[MILK_INDEX] += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.ingredientsWithTheMachine[COFFEE_INDEX] += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.ingredientsWithTheMachine[CUP_INDEX] += scanner.nextInt();
    }

    public void processTake() {
        System.out.printf("I gave you $%d%n", this.ingredientsWithTheMachine[AMOUNT_INDEX] );
        this.ingredientsWithTheMachine[AMOUNT_INDEX] = 0;
    }

    public void processAction() {
        boolean proceed = true;
        do {
            System.out.printf("Write action (%s, %s, %s, %s, %s):%n", BUY_ACTION, FILL_ACTION,
                    TAKE_ACTION, REMAINING_ACTION, EXIT_ACTION);
            String action = scanner.next();
            switch (action) {
                case BUY_ACTION:
                    processBuy();
                    break;
                case FILL_ACTION:
                    processFill();
                    break;
                case TAKE_ACTION:
                    processTake();
                    break;
                case REMAINING_ACTION:
                    displayMachineIngredients();
                    break;
                default:
                    proceed = false;
            }
        } while(proceed==true);
    }

    public static void main(String[] args) {
        CoffeeMachine machine = getCoffeeMachine();
        machine.processAction();

    }




}
