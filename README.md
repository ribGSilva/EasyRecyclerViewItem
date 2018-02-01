# ItemRecyclerViewExpansivel

Item of RecyclerView expansible.

Ex:

Gradle: 

        compile 'com.android.support:appcompat-v7:27.0.2'
        compile 'com.android.support:recyclerview-v7:27.0.2'
        compile 'com.android.support:design:27.0.2'

XML - main: 

        <?xml version="1.0" encoding="utf-8"?>
        <android.support.design.widget.CoordinatorLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MainActivity">

            <android.support.v7.widget.RecyclerView
                android:id="@+id/recycler"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />

        </android.support.design.widget.CoordinatorLayout>

XML - fixed_layout 

      <?xml version="1.0" encoding="utf-8"?>
      <LinearLayout
          xmlns:android="http://schemas.android.com/apk/res/android"
          android:layout_width="match_parent"
          android:layout_height="match_parent">

          <TextView
              android:id="@+id/main"
              android:textSize="30sp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />

      </LinearLayout>

XML - expansible_layout

      <?xml version="1.0" encoding="utf-8"?>
      <LinearLayout
          xmlns:android="http://schemas.android.com/apk/res/android"
          android:layout_width="match_parent"
          android:layout_height="match_parent">

          <TextView
              android:id="@+id/body"
              android:textSize="20sp"
              android:layout_width="match_parent"
              android:layout_height="wrap_content" />

      </LinearLayout>

JAVA:

      RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
      
      //select the type of layout
      recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
  
      List<TestClass> listItems = new ArrayList<>();

      listItems.add(new TestClass("test1", "body1"));
      listItems.add(new TestClass("test2", "body2"));
      listItems.add(new TestClass("test3", "body3"));
      listItems.add(new TestClass("test4", "body4"));
      listItems.add(new TestClass("test5", "body5"));
      listItems.add(new TestClass("test6", "body6"));
      listItems.add(new TestClass("test7", "body7"));
      listItems.add(new TestClass("test8", "body8"));
      listItems.add(new TestClass("test9", "body9")); 
        
      //handler which will fill the items
        BindExpansibleViewHolderHandler bindExpansibleViewHolderHandler =
              new EBindExpansibleViewHolderHandler() {
                    @Override
                    public void bindViewHolderHandler(
                                ExpansibleItemRecyclerViewAdapter.ExpansibleItemRecyclerViewViewHolder holder, 
                                Object object,
                                int position) {

                        //cast your item
                        TestClass item = (TestClass) object;

                        //get the layout fixed
                        View fixed = holder.getFixedFrame();

                        //get the expansible layout
                        View expansible = holder.getExpansibleFrame();

                        //the go fill with your class and layout

                        TextView title = (TextView) fixed.findViewById(R.id.main);
                        title.setText(item.getTitle());


                        TextView body = (TextView) expansible.findViewById(R.id.body);
                        body.setText(item.getBody());
                    }
                };
                
       recyclerView.setAdapter(ExpansibleItemRecyclerViewAdapter.newInstance(
                this, //context
                listItems, //list of items
                R.layout.fixed_layout, // layout fixed
                R.layout.expansible_layout, // dynamic layout
                bindExpansibleViewHolderHandler, // handler that will fill the view items
        true // set if the dynamic layout will start visible or not
        ));


