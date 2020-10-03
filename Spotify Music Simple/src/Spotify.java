import java.io.*;
import java.util.*;

public class Spotify {

    public static void main(String[] args) throws IOException
    {
        try
        {
            String[][] list = arrayCreator();

            //arrayCreator() help get the size of the rows and column of the .cvs file by using for-loop

            Scanner readFile = new Scanner(new File("regional-global-daily-latest.csv"));
            String description = readFile.nextLine();
            //this was used to remove the file description so this wouldn't be in the 2d Array

            String header = readFile.nextLine();
            System.out.println(header.replaceAll(",", " "));
            //just a variable to find the Position, Trackname, artist, streams, url

            while(readFile.hasNextLine())
            {
               for(int i = 0; i < list.length; i++)
               {
                   String line = readFile.nextLine();
                   String[] temp = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                   for(int j = 0; j < list[i].length; j++)
                   {
                       list[i][j] = temp[j].replaceAll("\"", "").strip();

                   }
               }
            }

            //this loop read the file line by line.
            //one row at a time and one column at a time
            //the split method separate them by commas
            //then put into the 2d array

            printMyList(list);
            //print out myList
            System.out.println();

            String[] artist = new String[list.length];
            //loop for just artist name

            for(int i = 0; i < list.length; i++)
            {
                artist[i] = list[i][2];;
            }
            //this loop put only artist name into the array

            quicksort(artist, 0, artist.length - 1);
            //a method to sort them into ABC order
            


            int[] countList = countDup(artist);
            //count the dup and put it into a int array

            String[] artists = duplicate(artist);
            //remove dup and put it into another array for just single artist name

            LinkedList listy = new LinkedList();

            for(int i = 0; i < artists.length; i++)
            {
                listy.insert(artists[i]);
            }

            System.out.println("Top " + listy.getSize() + " streaming artists:");
            listy.printList();





            String[][] myList = new String[artists.length][2];
            //this is two combine countList and artists into one 2d array
            // [0][0] = artists name [0][1] = number it appears
            System.out.println();
            System.out.println("Number of times an artist appearance in the list: ");
                for(int j = 0; j < myList.length; j++)
                {
                    myList[j][0] = artists[j];
                    myList[j][1] = Integer.toString(countList[j]);
                    System.out.println(myList[j][0] + " appears: " + myList[j][1] + " times.");
                }
                //basically printing it out

                File file = new File("Artists-WeekOf09052020.txt");
                file.createNewFile();
                FileWriter writeFile = new FileWriter(file);
                writeFile.write("Number of times an artist appearance in the list:\n");

                for(int i = 0; i < myList.length; i++)
                {
                    writeFile.write(myList[i][0] + " appears: " + myList[i][1] + " times.\n");
                }

            writeFile.write("\n");
                writeFile.write("Top " + listy.getSize() + " streaming artists:\n");

                for(int i = 0; i < myList.length; i++)
                {
                    if(!listy.getIndex(i).equals("null"))
                    {
                        writeFile.write(listy.getIndex(i) + "\n");
                    }else{
                        System.out.println("There is nothing to be written");
                        break;
                    }
                }

                writeFile.flush();
                writeFile.close();



        }catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
    }

    public static String[][] arrayCreator() throws FileNotFoundException
    {
        Scanner readFile = new Scanner(new File("regional-global-daily-latest.csv"));
        int rowCount = 0;
        int columnCount = 0;
        String description = readFile.nextLine();
        String header = readFile.nextLine();
        String[] temp = header.split(",");
        columnCount = temp.length;

        while (readFile.hasNextLine()) {
            readFile.nextLine();
            rowCount++;
        }

        String[][] list = new String[rowCount][columnCount];
        return list;
    }

    public static void quicksort(String[] artistList, int low, int high)
    {
        int i = low, j = high;
        String pivot = artistList[low + (high - low)/2];
        while(i<=j)
        {
            while(artistList[i].compareToIgnoreCase(pivot) < 0)
            {
                i++;
            }
            while(artistList[j].compareToIgnoreCase(pivot) > 0)
            {
                j--;
            }

            if(i <= j)
            {
                swap(artistList, i, j);
                i++;
                j--;
            }
        }

        if(low < j)
        {
            quicksort(artistList, low, j);
        }

        if(i < high)
        {
            quicksort(artistList, i, high);
        }
    }

    public static void swap(String[] artistList, int i, int j)
    {
        String temp = artistList[i];
        artistList[i] = artistList[j];
        artistList[j] = temp;
    }

    public static String[] duplicate(String[] artistList)
    {
        int length = artistList.length;
        String[] temp = new String[length];
        int j = 0;



        for(int i = 0; i < length - 1; i++)
        {

            if(!artistList[i].equalsIgnoreCase(artistList[i+1]))
            {
                temp[j++] = artistList[i];
            }
        }

        temp[j++] = artistList[length - 1];

        String[] te = new String[j];

        for(int i = 0; i < j; i++)
        {
            te[i] = temp[i];
        }


        return te;
    }

    public static int[] countDup(String[] artist)
    {
        int count = 1;
        int[] x = new int[artist.length];
        for(int i = 0; i < artist.length - 1; i++)
        {
            for(int j = i + 1; j < artist.length; j++)
            {
                if(artist[i].equalsIgnoreCase(artist[j]))
                {
                    x[i] = ++count;
                }else{
                    x[i] = count;
                   }
            }
            count = 1;
        }
       return x;


    }

    public static void printMyList(String[][] myList)
    {
        for(int row = 0; row < myList.length; row++)
        {
            System.out.println(myList[row][0] + "  " + myList[row][1] + "  " + myList[row][2] + "  " + myList[row][3] + "  " + myList[row][4]);
        }

    }


}
