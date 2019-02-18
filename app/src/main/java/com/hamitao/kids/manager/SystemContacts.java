package com.hamitao.kids.manager;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.provider.ContactsContract.Data;

import com.hamitao.framework.utils.Logger;
import com.hamitao.kids.model.ContactData;
import com.hamitao.kids.model.ContactModel;
import com.hamitao.requestframe.entity.QueryContactInfo;

import java.util.ArrayList;
import java.util.List;

public class SystemContacts {
    //读取通讯录的全部的联系人
//需要先在raw_contact表中遍历id，并根据id到data表中获取数据
    public static List<ContactModel> testReadAll(Context context) {
        List<ContactModel> lists = new ArrayList<>();
        //uri = content://com.android.contacts/contacts
        Uri uri = Uri.parse("content://com.android.contacts/contacts"); //访问raw_contacts表
        ContentResolver resolver = context.getContentResolver();
        //获得_id属性
        Cursor cursor = resolver.query(uri, new String[]{Data._ID}, null, null, null);
        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
//            StringBuilder buf = new StringBuilder();
            //获得id并且在data中寻找数据
            int id = cursor.getInt(0);
            contactModel.setId(id);
//            buf.append("id=" + id);
            uri = Uri.parse("content://com.android.contacts/contacts/" + id + "/data");
            //data1存储各个记录的总数据，mimetype存放记录的类型，如电话、email等
            Cursor cursor2 = resolver.query(uri, new String[]{Data.DATA1, Data.MIMETYPE}, null, null, null);
            while (cursor2.moveToNext()) {
                String data = cursor2.getString(cursor2.getColumnIndex("data1"));
                if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/name")) {       //如果是名字
//                    buf.append(",name=" + data);
                    contactModel.setName(data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/phone_v2")) {  //如果是电话
//                    buf.append(",phone=" + data);
                    contactModel.setNumber(data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/email_v2")) {  //如果是email
//                    buf.append(",email=" + data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/postal-address_v2")) { //如果是地址
//                    buf.append(",address=" + data);
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/organization")) {  //如果是组织
//                    buf.append(",organization=" + data);
                }
            }
            lists.add(contactModel);
//            String str = buf.toString();
//            Log.i("Contacts", str);

        }
        return lists;
    }

    //根据电话号码查询姓名（在一个电话打过来时，如果此电话在通讯录中，则显示姓名）
    public static void testReadNameByPhone(Context context) {
        String phone = "12345678";
        //uri=  content://com.android.contacts/data/phones/filter/#
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + phone);
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{Data.DISPLAY_NAME}, null, null, null); //从raw_contact表中返回display_name
        if (cursor.moveToFirst()) {
            Log.i("Contacts", "name=" + cursor.getString(0));
        }
    }

    //一步一步添加数据
    public static void testAddContacts(Context context) {
        //插入raw_contacts表，并获取_id属性
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        long contact_id = ContentUris.parseId(resolver.insert(uri, values));
        //插入data表
        uri = Uri.parse("content://com.android.contacts/data");
        //add Name
        values.put("raw_contact_id", contact_id);
        values.put(Data.MIMETYPE, "vnd.android.cursor.item/name");
        values.put("data2", "zdong");
        values.put("data1", "xzdong");
        resolver.insert(uri, values);
        values.clear();
        //add Phone
        values.put("raw_contact_id", contact_id);
        values.put(Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
        values.put("data2", "2");   //手机
        values.put("data1", "87654321");
        resolver.insert(uri, values);
        values.clear();
        //add email
        values.put("raw_contact_id", contact_id);
        values.put(Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
        values.put("data2", "2");   //单位
        values.put("data1", "xzdong@xzdong.com");
        resolver.insert(uri, values);
    }


    /**
     * 批量添加联系人
     *
     * @param context
     * @param contactModels
     */
    public static void testAddContactsAll(Context context, List<ContactModel> contactModels) {
        for (int i = 0; i < contactModels.size(); i++) {
            try {
                testAddContactsInTransaction(context, contactModels.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加联系人
     *
     * @param context
     * @throws Exception
     */
    public static void testAddContactsInTransaction(Context context, ContactModel contactModel) throws Exception {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        // 向raw_contact表添加一条记录
        //此处.withValue("account_name", null)一定要加，不然会抛NullPointerException
        ContentProviderOperation operation1 = ContentProviderOperation
                .newInsert(uri).withValue("account_name", null).build();
        operations.add(operation1);
        // 向data添加数据
        uri = Uri.parse("content://com.android.contacts/data");
        //添加姓名
        ContentProviderOperation operation2 = ContentProviderOperation
                .newInsert(uri).withValueBackReference("raw_contact_id", 0)
                //withValueBackReference的第二个参数表示引用operations[0]的操作的返回id作为此值
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", contactModel.getName()).build();
        operations.add(operation2);
        //添加手机数据
        ContentProviderOperation operation3 = ContentProviderOperation
                .newInsert(uri).withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data2", "2")
                .withValue("data1", contactModel.getNumber()).build();
        operations.add(operation3);
        resolver.applyBatch("com.android.contacts", operations);
    }

    /**
     * 批量删除联系人
     *
     * @param context
     * @param contactModels
     */
    public static void testDeleteAll(Context context, List<ContactModel> contactModels) {
        for (int i = 0; i < contactModels.size(); i++) {
            try {
                testDelete(context, contactModels.get(i).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete
     *
     * @param context
     * @throws Exception
     */
    public static void testDelete(Context context, String name) throws Exception {
        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{Data._ID}, "display_name=?", new String[]{name}, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            //根据id删除data中的相应数据
            resolver.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
        }
    }

    public static void testUpdate(Context context, String oldName, ContactModel contactModel) {
        try {
            testDelete(context, oldName);
            testAddContactsInTransaction(context, contactModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate(Context context) throws Exception {
        int id = 1;
        String phone = "999999";
        Uri uri = Uri.parse("content://com.android.contacts/data");//对data表的所有数据操作
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("data1", phone);
        resolver.update(uri, values, "mimetype=? and raw_contact_id=?", new String[]{"vnd.android.cursor.item/phone_v2", id + ""});
    }

    public static void refreshContacts(Context context, List<QueryContactInfo.ResponseDataObjBean.ContactsBean> contactsInfos) {
        List<ContactModel> contactModels = SystemContacts.testReadAll(context);
        SystemContacts.testDeleteAll(context, contactModels);

        for (int i = 0; i < contactsInfos.size(); i++) {
            ContactModel model = new ContactModel();
            String phoneName = contactsInfos.get(i).getPhonenum();
            String contactname = contactsInfos.get(i).getContactname();
            model.setName(contactname);
            model.setNumber(phoneName);
            try {
                testAddContactsInTransaction(context,model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
