package overlandthomas.shopinglistalpha;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Scanner;

import overlandthomas.shopinglistalpha.UnitConversions.Unit;
import overlandthomas.shopinglistalpha.UnitConversions.Units;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFood.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddFood#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFood extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Spinner units;
    public Spinner whole;
    public Spinner fraction;
    public EditText food;
    private OnFragmentInteractionListener mListener;

    /**
     * fragment to create a UI for inputing foods to the list
     * displayes a new UI and then communicates to main activity
     */
    public AddFood() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFood.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFood newInstance(String param1, String param2) {
        AddFood fragment = new AddFood();
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
        View v =inflater.inflate(R.layout.fragment_add_food, container, false);
        /*
        creates 3 spinners and assigns refrence variables to them
        assignes them values
        creates a text box and assigns a refrence variable to it
         */
        units = (Spinner) v.findViewById(R.id.unit);
        whole = (Spinner) v.findViewById(R.id.whole);
        fraction = (Spinner) v.findViewById(R.id.fraction);
        ArrayAdapter<CharSequence> u= ArrayAdapter.createFromResource(this.getActivity(), R.array.units, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> w= ArrayAdapter.createFromResource(this.getActivity(), R.array.whole, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> f= ArrayAdapter.createFromResource(this.getActivity(), R.array.fraction, android.R.layout.simple_spinner_item);
        u.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        w.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        f.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(u);
        whole.setAdapter(w);
        fraction.setAdapter(f);
        food = (EditText) v.findViewById(R.id.nameOfFood);
        return v;
    }

    /**
     * reads a button input from the xml
     * parses inputed data to send it to the main activity
     * @param v
     */
    public void addItem(View v){
        String n = food.toString();
        String unit = units.getSelectedItem().toString();
        String num = whole.getSelectedItem().toString();
        String frac = fraction.getSelectedItem().toString();
        //checks if user has inputed values to all fields
        // stops meathod if user hasn't
        if(n==null||unit==null||num==null||frac==null){
            return;
        }
        //takes the user inputed values from the spiners and parses them into a double
        Double val;
        Scanner s = new Scanner(num);
        val=s.nextDouble();
        s = new Scanner(frac);
        s.useDelimiter("/");
        if(!frac.equals("0")){
            int t = s.nextInt();
            double d = t/s.nextInt();
            val+=d;
        }
        Unit u = new Unit(unit,val, Units.getFamily(unit));
        mListener.add(u,n);
    }

    /**
     * reads a button input from the xml
     * when pressed it ends this fragment and swaps it back to the list
     * @param view
     */
    public void terminate(View view){
        mListener.end();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void end();
        void add(Unit u, String n);
    }

}
