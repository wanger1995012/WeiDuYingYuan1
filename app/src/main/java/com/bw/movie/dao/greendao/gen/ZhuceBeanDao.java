package com.bw.movie.dao.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bw.movie.bean.ZhuceBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ZHUCE_BEAN".
*/
public class ZhuceBeanDao extends AbstractDao<ZhuceBean, Void> {

    public static final String TABLENAME = "ZHUCE_BEAN";

    /**
     * Properties of entity ZhuceBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Birthday = new Property(0, long.class, "birthday", false, "BIRTHDAY");
        public final static Property HeadPic = new Property(1, String.class, "headPic", false, "HEAD_PIC");
        public final static Property LastLoginTime = new Property(2, long.class, "lastLoginTime", false, "LAST_LOGIN_TIME");
        public final static Property NickName = new Property(3, String.class, "nickName", false, "NICK_NAME");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Sex = new Property(5, int.class, "sex", false, "SEX");
    }


    public ZhuceBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ZhuceBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ZHUCE_BEAN\" (" + //
                "\"BIRTHDAY\" INTEGER NOT NULL ," + // 0: birthday
                "\"HEAD_PIC\" TEXT," + // 1: headPic
                "\"LAST_LOGIN_TIME\" INTEGER NOT NULL ," + // 2: lastLoginTime
                "\"NICK_NAME\" TEXT," + // 3: nickName
                "\"PHONE\" TEXT," + // 4: phone
                "\"SEX\" INTEGER NOT NULL );"); // 5: sex
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ZHUCE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ZhuceBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getBirthday());
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(2, headPic);
        }
        stmt.bindLong(3, entity.getLastLoginTime());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(4, nickName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
        stmt.bindLong(6, entity.getSex());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ZhuceBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getBirthday());
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(2, headPic);
        }
        stmt.bindLong(3, entity.getLastLoginTime());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(4, nickName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
        stmt.bindLong(6, entity.getSex());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public ZhuceBean readEntity(Cursor cursor, int offset) {
        ZhuceBean entity = new ZhuceBean( //
            cursor.getLong(offset + 0), // birthday
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // headPic
            cursor.getLong(offset + 2), // lastLoginTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // nickName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
            cursor.getInt(offset + 5) // sex
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ZhuceBean entity, int offset) {
        entity.setBirthday(cursor.getLong(offset + 0));
        entity.setHeadPic(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLastLoginTime(cursor.getLong(offset + 2));
        entity.setNickName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSex(cursor.getInt(offset + 5));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(ZhuceBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(ZhuceBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(ZhuceBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
