package com.example.usuario.actionbarnavigationdrawer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ActionAndDrawerActivity extends Activity implements MyFragment.OnFragmentInteractionListener {

    private ListView list;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    public final static String SUBTITLE = "Subtitulo Perron";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        initUI();
        setAdapter();
        setClicks();
        setToggle();
    }

    private void initUI() {
        list = (ListView) findViewById(R.id.left_drawer);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void setAdapter() {
        DrawerAdapter adapter = new DrawerAdapter(this);
        list.setAdapter(adapter);
    }

    private void setClicks() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ActionAndDrawerActivity.this, "Menu Item: " + DrawerAdapter.menu[position], Toast.LENGTH_SHORT).show();
                drawer.closeDrawers();
                setColorAndTitleByChosenItem(position, DrawerAdapter.menu[position]);
            }
        });
    }

    private void setColorAndTitleByChosenItem(int position, String title) {
        getActionBar().setTitle(title);
        switch (position) {
            case 0:
                addFragment(R.color.aqua);
            case 1:
                addFragment(R.color.lime);
                break;
            case 2:
                addFragment(R.color.navy);
                break;
            case 3:
                addFragment(R.color.olive);
                break;

        }
    }

    private void addFragment(int color) {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, MyFragment.getInstance(color, SUBTITLE)).commit();
    }

    // Se tiene que sincronizar antes de iniciar el activity en el onResume
    @Override
    protected void onResume() {
        super.onResume();
        mDrawerToggle.syncState();
    }

    private void setToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.drawable.ic_drawer,
                R.string.action_settings,
                R.string.action_settings);
        drawer.setDrawerListener(mDrawerToggle);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Parte del ciclo de vida de la actividad.Verifica si existe un menu a crear.Si lo hay lo regreso para que lo agregue a la actividad
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }



    // Cuando se de un click a un menu darle una funcionalidad en especifico.
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Intent intent = null;
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_help:
                Toast.makeText(this,"Click Help",Toast.LENGTH_SHORT).show();
                //addFragment(R.color.navy);

                intent = new Intent(ActionAndDrawerActivity.this, ActivityHelp.class);
                startActivityForResult(intent,200);
                break;
            case R.id.menu_settings:
                Toast.makeText(this,"Click Settings",Toast.LENGTH_SHORT).show();
                //addFragment(R.color.red);break;
                intent = new Intent(ActionAndDrawerActivity.this, ActivitySettings.class);
                startActivityForResult(intent,300);
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        writeTitleOrSubtitle(resultCode, data);
    }

    private void writeTitleOrSubtitle(int resultCode, Intent data) {
        switch (resultCode){
            case 400:String subtitle=(String) data.getExtras().get("Subtitle");getActionBar().setSubtitle(subtitle);
            case 500:String title=(String) data.getExtras().get("Title");getActionBar().setTitle(title);
        }
    }

    @Override
    public void onFragmentInteraction(String subtitle) {
        getActionBar().setSubtitle(subtitle);
    }
}