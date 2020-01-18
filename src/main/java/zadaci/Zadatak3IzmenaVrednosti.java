package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;
import zadaci.Zadatak2DodavanjeVrednosti;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {

    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);


            List<Roba> roba=robaDao.queryForAll();
            for(Roba r:roba) {
                System.out.println("Roba = " +r.toString());
            }


            List<Roba> robaZaIzmenu = robaDao.queryForEq(Roba.POLJE_OPIS, "Plasticna stolica");

            for (Roba r: robaZaIzmenu) {
                r.setOpis("Drvena stolica");
                robaDao.update(r);
            }


            for ( Roba  r: roba ) {
                System.out.println("a"+r);
            }


            }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
