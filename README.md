# android-paging-sample
Android Architecture Components Paging Library Sample

Google Introduce new Paging Library in it’s architectural components .Paging library is useful in load information from a data source, without overloading the device or waiting too long for a big database query.

Apps usually handle large sets of data but need to show some data at once .if app will load all data at once then it will take so much time in network request ,database query and managing data in view.

Android have already paging concept in existing api but it have some drawbacks also

CursorAdapter  runs database queries on the UI thread, and pages content  with a Cursor. but it is not efficient in large database query .
AsyncListUtil it is useful for paging position-based data into a RecyclerView, but not useful  for non-positional paging,
That’s why Google introduce new Paging Library.

Paging Library have 4 main classes

DataSource  this class is useful for data query and loading data into PagedList.
PagedList     this class loads data from DataSource . we can set data limit in page, wait time for user and prefetch                        data .
PagedListAdapter : this is Adapter class which indicates the recyclerview that page is loaded.
LivePagedListBuilder : this class is useful to generate LiveData<PagedList>  from the DataSource.Factory.

more info at http://androidcodehub.com/paginglibrary/
