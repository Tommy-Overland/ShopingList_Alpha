package overlandthomas.shopinglistalpha;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import overlandthomas.shopinglistalpha.UnitConversions.Unit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainList extends Fragment implements Rmove{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ArrayList<FoodItem> foods = new ArrayList<>();//stores the food items for refrence
    public LinearLayout mainLayout ; //the main layout UI elements are added to
    public File listFile; //the file that the foods are saved to
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainList.
     */
    // TODO: Rename and change types and number of parameters
    public static MainList newInstance(String param1, String param2) {
        MainList fragment = new MainList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("info","List on create view called");
        View v=inflater.inflate(R.layout.fragment_main_list, container, false);

        mainLayout = (LinearLayout) v.findViewById(R.id.ListOfFood);//sets the value for the main layout
        /*
        FoodItem test = new FoodItem("test",this.getContext(),this.foods,new Unit ("cup",0,"volume"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.addView(test.layout,1,params);
        //mainLayout.addView(test.item);
        */
        return v;
    }
    public void onPause(){
        super.onPause();
        Log.d("info", "List on Pause");
        Save();//saves the data

    }
    public void onResume(){
        super.onResume();
        Log.d("info", "List on resume");
        //gets the file for the saving
        listFile = mListener.getFile();
        if(listFile==null){
            Log.d("info","null file in main");
            return;
        }
        if(listFile.exists()){
            try {
                Scanner sc = new Scanner(listFile);
                open(sc);//reads the file for any saved data
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }else{
            try {
                listFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void onStop(){
        super.onStop();
        Log.d("info", "List on stop");

        for(int i=0; i<foods.size();i++){
            mainLayout.removeView(foods.get(i).layout);
        }
        foods.clear();

    }
    public void onStart(){
        super.onStart();
        Log.d("info", "List on start");



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * saves the list in the storage file
     */
    public void Save(){
        Log.d("info","List save called");
        PrintStream p;
        //conviluted code that checks if the file exists makes a print stream if it doesn't it makes a new file and creates a new print steam
        try {
             p = new PrintStream(listFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            try {
                listFile.createNewFile();
                try{
                    p= new PrintStream(listFile);
                }catch (FileNotFoundException why){
                    why.printStackTrace();
                    p=System.out;
                }
            }catch (IOException wtf){
                wtf.printStackTrace();
                p=System.out;
            }
        }
        Log.d("info","List save called in foods");
        for(int i=0; i<foods.size();i++){
            p.println(foods.get(i).toString());
            Log.d("info","list saved "+foods.get(i).toString()+"index "+i);
        }

    }

    /**
     * opends the file tied to the scanner inputed
     * @param file
     */
    public void open(Scanner file){
        Log.d("info","List open called");
        while (file.hasNextLine()){
            String test = file.nextLine();
            Scanner line = new Scanner(test);
            line.useDelimiter("&");
            String item;
            String unit;
            String fam;
            double quant;
            if(line.hasNext()){
                item = line.next();
            }else{
                return;
            }
            if(line.hasNext()){
                unit = line.next();
            }else{
                return;
            }
            if(line.hasNext()){
                fam = line.next();
            }else{
                return;
            }
            if(line.hasNextDouble()){
                quant = line.nextDouble();
            }else{
                return;
            }
            FoodItem food = new FoodItem(item,this.getContext(),foods,new Unit(unit,quant,fam));
            addFood(food);
        }

    }
    /**
     * calls the getFood meathod
     * reads a button press to get a food item and add to list
     * @param view
     */
    public void get(View view){
        mListener.getFood();
    }

    /**
     * adds in a food item and then checks for duplicates
     * @param food
     */
    public void addFood(FoodItem food){
        Log.d("info","List add food called");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.addView(food.layout,0,params);
        //new food item is added to last index of array list
        //checks each index of arraylist for a matching item except the last spot

        for(int i=0; i<foods.size()-1;i++){
            //if it finds a matching item it consolidates the two items
            if(foods.get(i).food.equalsIgnoreCase(food.food)){
                foods.get(i).add(food.quantity);
                food.remove();
                return;
            }
        }

    }

    /**
     * removes a food item from the layout using the layouts .equals meathod
     * @param food
     */
    public void remove(FoodItem food){
        Log.d("info","List remove called");
        mainLayout.removeView(food.layout);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void getFood();
        File getFile();
    }
}
