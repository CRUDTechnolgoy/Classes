package com.crud.myfirstapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DataHelper {
    private static DataHelper instance;
    private SQLiteDatabase db;
    private DataManager dm;
    Context cx;

    public DataHelper(Context cx) {
        instance = this;
        this.cx = cx;
        dm = new DataManager(cx, DataManager.DATABASE_NAME, null, DataManager.DATABASE_VERSION);
    }

    public static DataHelper getInstance() {
        return instance;
    }

    public void open() {
        db = dm.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void read() {
        db = dm.getReadableDatabase();
    }

    public void delete() {
        open();
        db.delete(DataManager.TABLE_USERTABLE, null, null);
        close();
    }

    public int getLastNo() {
        int i = 0;
        read();
        Cursor cur = db.rawQuery("select * from " + DataManager.TABLE_USERTABLE + " order by " + DataManager.KEY_ID + " desc LIMIT 1", null);
        if (cur.moveToFirst()) {
            i = cur.getInt(0);
        }
        close();
        return i;
    }

    public int getNoOfRecord() {
        int i = 0;
        read();
        Cursor cur = db.rawQuery("select count(*) from " + DataManager.TABLE_USERTABLE + " ", null);
        if (cur.moveToFirst()) {
            i = cur.getInt(0);
        }
        close();
        return i;
    }

    public int checkUser(String name, String pwd) {
        int i = 0;
        read();
        Cursor cur = db.rawQuery("select * from " + DataManager.TABLE_USERTABLE + " where " + DataManager.KEY_NAME + "='" + name + "' and " + DataManager.KEY_PASSWORD + "='" + pwd + "'", null);
        if (cur.moveToFirst()) {
            i = cur.getInt(0);
        }
        close();
        return i;

    }


    public int updateUserDetail(String userName, String password, int id) {
        open();
        ContentValues values;
        try {
            values = new ContentValues();
            values.put(DataManager.KEY_NAME, userName);
            values.put(DataManager.KEY_PASSWORD, password);
            return db.update(DataManager.TABLE_USERTABLE, values, DataManager.KEY_ID + "=" + id, null);
        } catch (Exception e) {
            Log.e("data", e.getMessage());
        }
        close();
        return 0;
    }

    public Cursor getUserDetailByID(int id) {
        int i = 0;
        read();
        Cursor cur = db.rawQuery("select * from " + DataManager.TABLE_USERTABLE + " where " + DataManager.KEY_ID + "='" + id + "'", null);
        if (cur.moveToFirst()) {
            return cur;
        }
        close();
        return null;

    }

    public long insert(Student s) {
        open();
        ContentValues values = new ContentValues();
        values.put(DataManager.KEY_NAME, s.getName());
        values.put(DataManager.KEY_PASSWORD, s.getPassword());
        values.put(DataManager.KEY_CITY, s.getCity());
        try {
            return db.insert(DataManager.TABLE_USERTABLE, null, values);
        } catch (Exception e) {
            Log.e("data", e.getMessage());
        }
        close();
        return 0;
    }


  /*  public boolean genrateExcel() {
        read();
        try {
            Cursor cur = db.rawQuery("select * from " + DataManager.TABLE_NAME + " where " + DataManager.KEY_STATUS + "=0", null);
            if (cur.moveToFirst()) {
                exportToExcel(cur);
                return true;
            }
        } catch (Exception e) {
            M.E(e.getMessage());
        }
        close();
        return false;
    }


    private void exportToExcel(Cursor cursor) {
        final String fileName = Utility.getCurrentDateP() + ".xls";

        //Saving file in external storage
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/MARS");

        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }

        //file path
        File file = new File(directory.getAbsolutePath(), fileName);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook;
        M.E("3");
        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //Excel sheet name. 0 represents first sheet
            WritableSheet sheet = workbook.createSheet("MARS", 0);
            M.E("4");
            try {
                sheet.addCell(new Label(0, 0, "Identification no.")); // column and row
                sheet.addCell(new Label(1, 0, "Survey Code"));
                sheet.addCell(new Label(2, 0, "Name"));
                sheet.addCell(new Label(3, 0, "Age"));
                sheet.addCell(new Label(4, 0, "Sex"));
                sheet.addCell(new Label(5, 0, "Education"));
                sheet.addCell(new Label(6, 0, "Occupation"));
                sheet.addCell(new Label(7, 0, "Religion"));
                sheet.addCell(new Label(8, 0, "Per capita monthly income"));
                sheet.addCell(new Label(9, 0, "No. of family members"));
                sheet.addCell(new Label(10, 0, "Teeth present"));
                sheet.addCell(new Label(11, 0, "Decayed teeth"));
                sheet.addCell(new Label(12, 0, "Missed teeth"));
                sheet.addCell(new Label(13, 0, "Filled teeth"));
                sheet.addCell(new Label(14, 0, "Total DMFT SUM"));
                sheet.addCell(new Label(15, 0, "Decayed surfaces"));
                sheet.addCell(new Label(16, 0, "Missed surfaces"));
                sheet.addCell(new Label(17, 0, "Filled surfaces"));
                sheet.addCell(new Label(18, 0, "Total DMFS SUM"));
                sheet.addCell(new Label(19, 0, "Dean's Fluorosis score"));
                sheet.addCell(new Label(20, 0, "U/L"));
                sheet.addCell(new Label(21, 0, "Tooth No."));
                sheet.addCell(new Label(22, 0, "CPI score-17/16"));
                sheet.addCell(new Label(23, 0, "CPI score-11"));
                sheet.addCell(new Label(24, 0, "CPI score-26/27"));
                sheet.addCell(new Label(25, 0, "CPI score-46/47"));
                sheet.addCell(new Label(26, 0, "CPI score-31"));
                sheet.addCell(new Label(27, 0, "CPI score-36/37"));
                sheet.addCell(new Label(28, 0, "CPI Total"));
                sheet.addCell(new Label(29, 0, "LOA score-17/16"));
                sheet.addCell(new Label(30, 0, "LOA score-11"));
                sheet.addCell(new Label(31, 0, "LOA score-26/27"));
                sheet.addCell(new Label(32, 0, "LOA score-46/47"));
                sheet.addCell(new Label(33, 0, "LOA score-31"));
                sheet.addCell(new Label(34, 0, "LOA score-36/37"));
                sheet.addCell(new Label(35, 0, "LOA Total"));
                sheet.addCell(new Label(36, 0, "DI scores-16"));
                sheet.addCell(new Label(37, 0, "DI scores-11"));
                sheet.addCell(new Label(38, 0, "DI scores-26"));
                sheet.addCell(new Label(39, 0, "DI scores-36"));
                sheet.addCell(new Label(40, 0, "DI scores-31"));
                sheet.addCell(new Label(41, 0, "DI scores-46"));
                sheet.addCell(new Label(42, 0, "DI Total"));
                sheet.addCell(new Label(43, 0, "CI scores-16"));
                sheet.addCell(new Label(44, 0, "CI scores-11"));
                sheet.addCell(new Label(45, 0, "CI scores-26"));
                sheet.addCell(new Label(46, 0, "CI scores-36"));
                sheet.addCell(new Label(47, 0, "CI scores-31"));
                sheet.addCell(new Label(48, 0, "CI scores-46"));
                sheet.addCell(new Label(49, 0, "CI Total"));
                sheet.addCell(new Label(50, 0, "OHI-S SUM"));
                sheet.addCell(new Label(51, 0, "Interpretation"));
                sheet.addCell(new Label(52, 0, "General Information"));
                sheet.addCell(new Label(53, 0, "Plaque scores-16"));
                sheet.addCell(new Label(54, 0, "Plaque scores-12"));
                sheet.addCell(new Label(55, 0, "Plaque scores-24"));
                sheet.addCell(new Label(56, 0, "Plaque scores-36"));
                sheet.addCell(new Label(57, 0, "Plaque scores-32"));
                sheet.addCell(new Label(58, 0, "Plaque scores-44"));
                sheet.addCell(new Label(59, 0, "PLAQUE SUM"));
                sheet.addCell(new Label(60, 0, "Interpretation"));
                sheet.addCell(new Label(61, 0, "Any Other information"));
                sheet.addCell(new Label(62, 0, "Date Added"));
                M.E("5");
                if (cursor.moveToFirst()) {
                    do {
                        int i = cursor.getPosition() + 1;
                        M.E(i + "");
                        sheet.addCell(new Label(0, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_ID))));
                        sheet.addCell(new Label(1, i, SharedPreference.getInstance(cx).getString(SharedPreference.SERVER_CODE)));
                        sheet.addCell(new Label(2, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_NAME))));
                        sheet.addCell(new Label(3, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_AGE))));
                        sheet.addCell(new Label(4, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_GENDER))));
                        sheet.addCell(new Label(5, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_EDUCATION))));
                        sheet.addCell(new Label(6, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_OCCUPATION))));
                        sheet.addCell(new Label(7, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_RELIGION))));
                        sheet.addCell(new Label(8, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_MONTHLYINCOME))));
                        sheet.addCell(new Label(9, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_FAMILYMEMBERS))));
                        sheet.addCell(new Label(10, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TEETHPRESENT))));
                        sheet.addCell(new Label(11, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALDECAYED))));
                        sheet.addCell(new Label(12, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALMISSED))));
                        sheet.addCell(new Label(13, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALFILLED))));
                        sheet.addCell(new Label(14, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALDMFT))));
                        sheet.addCell(new Label(15, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALDECAYED_SURFACE))));
                        sheet.addCell(new Label(16, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALMISSED_SURFACE))));
                        sheet.addCell(new Label(17, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALFILLED_SURFACE))));
                        sheet.addCell(new Label(18, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOTALDMFS_SURFACE))));
                        sheet.addCell(new Label(19, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_MODIFIEDDEANS))));
                        sheet.addCell(new Label(20, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_UL))));
                        sheet.addCell(new Label(21, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_TOOTHNO))));
                        sheet.addCell(new Label(22, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CU1))));
                        sheet.addCell(new Label(23, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CU2))));
                        sheet.addCell(new Label(24, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CU3))));
                        sheet.addCell(new Label(25, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CU4))));
                        sheet.addCell(new Label(26, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CU5))));
                        sheet.addCell(new Label(27, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CU6))));
                        sheet.addCell(new Label(28, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_COMMUNITYPERIODONTAL))));
                        sheet.addCell(new Label(29, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LU1))));
                        sheet.addCell(new Label(30, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LU2))));
                        sheet.addCell(new Label(31, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LU3))));
                        sheet.addCell(new Label(32, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LU4))));
                        sheet.addCell(new Label(33, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LU5))));
                        sheet.addCell(new Label(34, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LU6))));
                        sheet.addCell(new Label(35, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_LOSSOFATTACHMENT))));
                        sheet.addCell(new Label(36, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS1))));
                        sheet.addCell(new Label(37, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS2))));
                        sheet.addCell(new Label(38, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS3))));
                        sheet.addCell(new Label(39, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS4))));
                        sheet.addCell(new Label(40, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS5))));
                        sheet.addCell(new Label(41, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS6))));
                        sheet.addCell(new Label(42, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_DS_TOTAL))));
                        sheet.addCell(new Label(43, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS1))));
                        sheet.addCell(new Label(44, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS2))));
                        sheet.addCell(new Label(45, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS3))));
                        sheet.addCell(new Label(46, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS4))));
                        sheet.addCell(new Label(47, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS5))));
                        sheet.addCell(new Label(48, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS6))));
                        sheet.addCell(new Label(49, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_CS_TOTAL))));
                        sheet.addCell(new Label(50, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_OHI_S_SUM))));
                        sheet.addCell(new Label(51, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_ORALHYGIENE))));
                        sheet.addCell(new Label(52, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_GENRALINFO))));
                        sheet.addCell(new Label(53, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI1))));
                        sheet.addCell(new Label(54, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI2))));
                        sheet.addCell(new Label(55, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI3))));
                        sheet.addCell(new Label(56, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI4))));
                        sheet.addCell(new Label(57, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI5))));
                        sheet.addCell(new Label(58, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI6))));
                        sheet.addCell(new Label(59, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PI_TOTAL))));
                        sheet.addCell(new Label(60, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_PLAQUEINDEX))));
                        sheet.addCell(new Label(61, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_ANYOTHERINFO))));
                        sheet.addCell(new Label(62, i, cursor.getString(cursor.getColumnIndex(DataManager.KEY_ADDEDDATE))));
                    } while (cursor.moveToNext());
                }
                //closing cursor
                cursor.close();
            } catch (RowsExceededException e) {
                M.E(e.getMessage());
            } catch (WriteException e) {
                M.E(e.getMessage());
            }
            workbook.write();
            try {
                workbook.close();
            } catch (WriteException e) {
                M.E(e.getMessage());
            }
        } catch (IOException e) {
            M.E(e.getMessage());
        }
    }
*/

//	public void insert_categories(Category cats[])
//	{
//		open();
//		db.delete("category", null, null);
//		for (int i = 0; i < cats.length; i++)
//		{
//			ContentValues values = new ContentValues();
//			values.put("categoryid", cats[i].getId());
//			values.put("category", cats[i].getCategory());
//			values.put("parent_id", cats[i].getParent_id());
//			values.put("slug", cats[i].getSlug());
//			values.put("status", cats[i].getStatus());
//			values.put("created_date", cats[i].getCreated_date());
//			values.put("parent", cats[i].getParent());
//			db.insertWithOnConflict("category", null, values, SQLiteDatabase.CONFLICT_REPLACE);
//		}
//		close();
//	}
//
//	public ArrayList<Category> get_main_categories()
//	{
//		open();
//		ArrayList<Category> cats = new ArrayList<Category>();
//		Cursor cur = db.rawQuery("select * from category where parent_id='0'", null);
//		while (cur.moveToNext())
//		{
//			cats.add(new Category(cur.getString(1), cur.getString(2),cur.getString(3), cur.getString(4), cur.getString(5), cur.getString(6), cur.getString(7)));
//		}
//		cur.close();
//		close();
//		return cats;
//	}
//
//	public void insert_books(BookDetail books[])
//	{
//		open();
//        db.delete("books", null, null);
//		for (int i = 0; i < books.length; i++)
//		{
//			ContentValues values = new ContentValues();
//			values.put("booksid", books[i].getId());
//			values.put("category",books[i].getCategory());
//			values.put("isbn_no", books[i].getIsbn_no());
//			values.put("slug", books[i].getSlug());
//			values.put("weight", books[i].getWeight());
//			values.put("main_image", books[i].getMain_image());
//			values.put("thumb_image", books[i].getThumb_image());
//			values.put("binding", books[i].getBinding());
//			values.put("featured_product", books[i].getFeatured_product());
//			values.put("avilable_item", books[i].getAvilable_item());
//			values.put("discount_item", books[i].getDiscount_item());
//			values.put("latest_book", books[i].getLatest_book());
//			values.put("papular_product", books[i].getPapular_product());
//			values.put("best_seller", books[i].getBest_seller());
//			values.put("no_of_pages", books[i].getNo_of_pages());
//			values.put("author_name", books[i].getAuthor_name());
//			values.put("publisher_name", books[i].getPublisher_name());
//			values.put("price", books[i].getPrice());
//			values.put("discount_percentage", books[i].getDiscount_percentage());
//			values.put("free_shipping", books[i].getFree_shipping());
//			values.put("name", books[i].getName());
//			values.put("description", books[i].getDescription());
//			values.put("edition", books[i].getEdition());
//			values.put("status", books[i].getStatus());
//			values.put("is_customized", books[i].getIs_customized());
//			values.put("is_purchased", books[i].getIs_purchased());
//			values.put("created", books[i].getCreated());
//			values.put("modified", books[i].getModified());
//			values.put("dispatchedIn", books[i].getDispatchedIn());
//			values.put("deliveredIn", books[i].getDeliveredIn());
//			values.put("cod_available", books[i].getCod_available());
//			values.put("rating", books[i].getRating());
//			db.insertWithOnConflict("books", null, values, SQLiteDatabase.CONFLICT_REPLACE);
//		}
//		close();
//	}
//
//	public ArrayList<BookDetail> get_featured()
//	{
//		ArrayList<BookDetail> al_books=new ArrayList<>();
//		read();
//		Cursor c = db.rawQuery("select * from books where featured_product='1'", null);
//		while (c.moveToNext())
//		{
//			BookDetail bd=new BookDetail(false,c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11),c.getString(12),c.getString(13), c.getString(14),c.getString(15),c.getString(16), c.getString(17), c.getString(18), c.getString(19), c.getString(20), c.getString(21),c.getString(22), c.getString(23),c.getString(24),c.getString(25),c.getString(26), c.getString(27), c.getString(28), c.getString(29), c.getString(30), c.getString(31), null, c.getString(32));
//			al_books.add(bd);
//		}
//		close();
//		return al_books;
//	}
//
//	public ArrayList<BookDetail> get_latest()
//	{
//		ArrayList<BookDetail> al_books=new ArrayList<>();
//		read();
//		Cursor c = db.rawQuery("select * from books where latest_book='1'", null);
//		while (c.moveToNext())
//		{
//			BookDetail bd=new BookDetail(false,c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11),c.getString(12),c.getString(13), c.getString(14),c.getString(15),c.getString(16), c.getString(17), c.getString(18), c.getString(19), c.getString(20), c.getString(21),c.getString(22), c.getString(23),c.getString(24),c.getString(25),c.getString(26), c.getString(27), c.getString(28), c.getString(29), c.getString(30), c.getString(31), null, c.getString(32));
//			al_books.add(bd);
//		}
//		close();
//		return al_books;
//	}
//
//	public ArrayList<BookDetail> get_popular()
//	{
//		ArrayList<BookDetail> al_books=new ArrayList<>();
//		read();
//		Cursor c = db.rawQuery("select * from books where papular_product='1'", null);
//		while (c.moveToNext())
//		{
//			BookDetail bd=new BookDetail(false,c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11),c.getString(12),c.getString(13), c.getString(14),c.getString(15),c.getString(16), c.getString(17), c.getString(18), c.getString(19), c.getString(20), c.getString(21),c.getString(22), c.getString(23),c.getString(24),c.getString(25),c.getString(26), c.getString(27), c.getString(28), c.getString(29), c.getString(30), c.getString(31), null, c.getString(32));
//			al_books.add(bd);
//		}
//		close();
//		return al_books;
//	}
//
//	public ArrayList<BookDetail> get_best()
//	{
//		ArrayList<BookDetail> al_books=new ArrayList<>();
//		read();
//		Cursor c = db.rawQuery("select * from books where best_seller='1'", null);
//		while (c.moveToNext())
//		{
//			BookDetail bd=new BookDetail(false,c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5),c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11),c.getString(12),c.getString(13), c.getString(14),c.getString(15),c.getString(16), c.getString(17), c.getString(18), c.getString(19), c.getString(20), c.getString(21),c.getString(22), c.getString(23),c.getString(24),c.getString(25),c.getString(26), c.getString(27), c.getString(28), c.getString(29), c.getString(30), c.getString(31), null, c.getString(32));
//			al_books.add(bd);
//		}
//		close();
//		return al_books;
//	}

}
