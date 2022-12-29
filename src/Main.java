import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Item> items = new ArrayList<>();
        items.add(new Item(356.4, 10));
        items.add(new Item(13.75, 40));
        items.add(new Item(136.63, 70));
        items.add(new Item(13, 300));
        items.add(new Item(130, 200));


        double maxWeight = 200;
        System.out.println("Max Cost = " + findMaxCost(items, maxWeight));
        System.out.println("-----------------------------------------");
        System.out.println("Best set of items: " + findBestSetOfItems(items, maxWeight));
        System.out.println("-----------------------------------------");
        List<List<Item>> allSet = getAllSubsets(items);
        System.out.println("All combinations of sets:");
        for (int i = 0; i < allSet.size(); i++) {
            System.out.println(allSet.get(i));
        }
    }

    public static double findMaxCost(List<Item> items, double maxWeight) {
        double maxCost = 0;
        for (int mask = 0; mask < (1 << items.size()); mask++) {
            double totalCost = 0;      //��������� ���������
            double totalWeight = 0;    //��������� ���
            for (int index = 0; index < items.size(); index++) {

                int value = (mask >> index) & 1;
                if (value == 1) {
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                }
            }
            //�������� ������ �� ����� ����. ���� ����� ��� ������ ��� ����� ����������� �����������
            if (totalWeight <= maxWeight) {
                //�������� ����� ���� ������ ���� ���� ���� ����������, ����� ��������� maxCost
                maxCost = Math.max(maxCost, totalCost);

            }

        }

        return maxCost;
    }


    //��_1:	public static List<Item> findBestSetOfItems(double maxWeight) � ����� ������ ����������
    // ������ (����������� arrayList) �������� ������ Item, ��������� ��������� ������� �����������,
    // ��� ���� �� ��������� maxWeight.
    public static List<Item> findBestSetOfItems(List<Item> items, double maxWeight) {
        double maxCost = 0;
        ArrayList<Item> myBestSetOfItems = new ArrayList<>(); //��� �� ����� ����������
        for (int mask = 0; mask < (1 << items.size()); mask++) {
            double totalCost = 0;      //��������� ���������
            double totalWeight = 0;    //��������� ���
            for (int index = 0; index < items.size(); index++) {

                int value = (mask >> index) & 1;
                if (value == 1) {

                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                }
            }
            if (totalWeight < maxWeight) {
                if (totalCost > maxCost) {
                    if (!myBestSetOfItems.isEmpty()) {
                        myBestSetOfItems.clear();
                    }
                    for (int index = 0; index < items.size(); index++) {
                        int value = (mask >> index) & 1;
                        if (value == 1) {
                            myBestSetOfItems.add(new Item(items.get(index).getCost(), items.get(index).getWeight()));
                        }
                    }
                }
            }
        }
        return myBestSetOfItems;
    }

    //��_2.	public static List<List<Item>> getAllSubsets(List<Item> items) � ����� ���������� ������������ ����������
    // ����� ����� � ���� ������ �������. �������� ��������: List<List<Item> �������� ������, ������� ������ ������,
    // ������� ������ Item. �.� �� ����� ���-�� �����:
    //{{}, {item1}, {item1, item2}, {item2}, {item3}, {item1, item2, item3}, {item1, item3}}.

    public static List<List<Item>> getAllSubsets(List<Item> items) {
        List<List<Item>> allSubsets = new ArrayList<List<Item>>();

        for (int mask = 0; mask < (1 << items.size()); mask++) {
            List<Item> myTemp = new ArrayList<>();
            for (int index = 0; index < items.size(); index++) {
                int value = (mask >> index) & 1;
                if (value == 1) {
                    myTemp.add(new Item(items.get(index).getCost(), items.get(index).getWeight()));
                }
            }
            allSubsets.add(myTemp);
        }
        return allSubsets;
    }


}