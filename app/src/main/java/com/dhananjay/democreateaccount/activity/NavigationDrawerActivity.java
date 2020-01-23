package com.dhananjay.democreateaccount.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.adapters.DirectoryNodeBinder;
import com.dhananjay.democreateaccount.adapters.FileNodeBinder;
import com.dhananjay.democreateaccount.adapters.TreeViewAdapter;
import com.dhananjay.democreateaccount.models.Dir;
import com.dhananjay.democreateaccount.models.File;
import com.dhananjay.democreateaccount.models.TreeNode;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TreeViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                 Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });*/
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
            R.id.nav_tools, R.id.nav_share, R.id.nav_send)*/
        /*AppBarConfiguration mAppBarConfiguration =
        new AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
            R.id.nav_tools, R.id.nav_share, R.id.nav_send)
            .setDrawerLayout(drawer)
            .build();*/
        /*NavController navController = Navigation.findNavController(this,
         R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController,
         mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(NavigationDrawerActivity.this,
                drawer,
                toolbar,
                R.string.open,
                R.string.close);

        toggle.setDrawerIndicatorEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setShowHideAnimationEnabled(true);
            actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_action_menu));
        }

        initView();
        initData();
    }

    private void initData() {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode<Dir> app = new TreeNode<>(new Dir("app"));
        nodes.add(app);
        app.addChild(
            new TreeNode<>(new Dir("manifests"))
                .addChild(new TreeNode<>(new File("AndroidManifest.xml")))
        );
        app.addChild(
            new TreeNode<>(new Dir("java")).addChild(
                new TreeNode<>(new Dir("tellh")).addChild(
                    new TreeNode<>(new Dir("com")).addChild(
                        new TreeNode<>(new Dir("recyclertreeview"))
                            .addChild(new TreeNode<>(new File("Dir")))
                            .addChild(new TreeNode<>(new File("DirectoryNodeBinder")))
                            .addChild(new TreeNode<>(new File("File")))
                            .addChild(new TreeNode<>(new File("FileNodeBinder")))
                            .addChild(new TreeNode<>(new File("TreeViewBinder")))
                    )
                )
            )
        );
        TreeNode<Dir> res = new TreeNode<>(new Dir("res"));
        nodes.add(res);
        res.addChild(
            new TreeNode<>(new Dir("layout")).lock() // lock this TreeNode
                .addChild(new TreeNode<>(new File("activity_main.xml")))
                .addChild(new TreeNode<>(new File("item_dir.xml")))
                .addChild(new TreeNode<>(new File("item_file.xml")))
        );
        res.addChild(
            new TreeNode<>(new Dir("mipmap"))
                .addChild(new TreeNode<>(new File("ic_launcher.png")))
        );

        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration mDividerItemDecoration =
            new DividerItemDecoration(rv.getContext(),
                DividerItemDecoration.VERTICAL);
        //mDividerItemDecoration.setDrawable(getResources()
        //    .getDrawable(R.drawable.recycler_divider, null));
        rv.addItemDecoration(mDividerItemDecoration);

        adapter = new TreeViewAdapter(nodes,
            Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder()));
        // whether collapse child nodes when their parent node was close.
        adapter.ifCollapseChildWhileCollapseParent(true);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    //Update and toggle the node.
                    onToggle(!node.isExpand(), holder);
//                    if (!node.isExpand())
//                        adapter.collapseBrotherNode(node);
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand,
                                 RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.ViewHolder dirViewHolder =
                    (DirectoryNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                ivArrow.animate().rotationBy(rotateDegree)
                    .start();
            }
        });
        rv.setAdapter(adapter);
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            adapter.collapseAll();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        /*NavController navController = Navigation.findNavController(this, R
        .id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
            ||*/
        //return super.onSupportNavigateUp();
        adapter.collapseAll();
        finish();
        return true;
    }
}
