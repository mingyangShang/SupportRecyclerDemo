# SupportRecyclerDemo
Demo using SupportRecyclerView and RecyclerCursorAdapter

- SupportRecyclerView
	- setEmptyView(View emptyView):set visiable empty view when none data.
	
- SupportRecyclerView.SupportAdapter
	- setOnItemClickListener(OnRecyclerViewItemClickListener itemClickListener):set click listener for recyclerview's item
	- setOnItemLongClickListener(OnRecyclerViewItemLongClickListener itemLongClickListener):set long click listener for recyclerview's item
	
- AbstractRecycleCursorAdapter
	- DemoRecyclerCursorAdapter(Context context,Cursor cursor):Constructor like CursorAdppter
	- onBindViewHolder(SupportRecyclerView.SupportViewHolder holder, Cursor cursor):bind viewholder and view,must be implemented by its subclass
	- SupportRecyclerView.SupportViewHolder onCreateViewHolder(ViewGroup parent, int viewType):create viewholder for each item,must be implemented by its subclass
	
- How to use

xml(If you copy the SupportRecyclerView file to your project,you should replace tag with SupportRecyclerView path in your project):

```
<com.smy.demo.supportrecyclerdemo.SupportRecyclerView
        android:id="@+id/demo_recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_above="@+id/layout_oper"/>
```

extend AbstractRecycleCursorAdapter:

```
public class DemoRecyclerCursorAdapter extends AbstractRecycleCursorAdapter{

    private LayoutInflater mInfalter;
    private Context mContext;

    public DemoRecyclerCursorAdapter(Context context,Cursor cursor){
        super(context,cursor);
        mContext = context;
        mInfalter = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(SupportRecyclerView.SupportViewHolder holder, Cursor cursor) {
        DemoViewHolder demoVH = (DemoViewHolder) holder;
        final int position = cursor.getPosition();
        demoVH.itemView.setTag(position);
        demoVH.tv.setText(cursor.getString(cursor.getColumnIndex(DemoDBHelper.NAME)));
    }

    @Override
    public SupportRecyclerView.SupportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(mInfalter.inflate(R.layout.recycler_item,parent,false));
    }


    public static class DemoViewHolder extends SupportRecyclerView.SupportViewHolder{
        public TextView tv;
        public DemoViewHolder(View view){
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
    
}

```

bind data and set listener:

```
    mRecyclerView = (SupportRecyclerView)findViewById(R.id.demo_recyclerview);   
    mRecyclerView.setEmptyView(findViewById(R.id.empty_view));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new DemoRecyclerCursorAdapter(this, mDbHelper.queryAll());
    mAdapter.setOnItemClickListener(this);
    mAdapter.setOnItemLongClickListener(this);
    mRecyclerView.setAdapter(mAdapter);
```