import ulloa.kenneth.gameswiki.model.dao.DB
import ulloa.kenneth.gameswiki.model.dao.SQLDAO
import ulloa.kenneth.gameswiki.model.entity.Desarrolladora
import ulloa.kenneth.gameswiki.model.entity.Genero
import ulloa.kenneth.gameswiki.model.entity.Plataforma
import ulloa.kenneth.gameswiki.model.entity.Videojuego
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Formats {
    companion object {
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    }
}


class DesarrolladoraDAO: SQLDAO<Desarrolladora, Int>() {
    private val sqlAll: String = "select * from desarrolladora"
    private val sqlId: String = "select * from desarrolladora where id=?"
    private val sqlUpdate: String = "update desarrolladora set nombre=?, ubicacion=?,anio=?,web=?,independiente=? where id=?;"
    private val sqlInsert: String = "insert into desarrolladora (nombre, ubicacion, anio, web, independiente) values (?,?,?,?,?);"
    private val sqlName: String = "select * from desarrolladora where nombre like ?"
    private val sqlDelete: String = "delete from desarrolladora where id=?"

    override fun loadFromResultSet(resultSet: ResultSet): Desarrolladora {
        return Desarrolladora(
            nombre = resultSet.getString("nombre"),
            ubicacion = resultSet.getString("ubicacion"),
            anioCreacion = resultSet.getInt("anio"),
            paginaWeb = resultSet.getString("web"),
            esIndependiente = resultSet.getString("independiente").equals("S"),
            id = resultSet.getInt("id")
        )
    }

    override fun getById(id: Int): Desarrolladora? {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlId);
        statement.setInt(1, id)
        val result: ResultSet = statement.executeQuery()
        result.next();
        if (result.row == 0) {
            return null;
        }
        return loadFromResultSet(result);
    }

    override fun getAll(): ArrayList<Desarrolladora> {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlAll)
        val results: ResultSet = statement.executeQuery();
        val list: ArrayList<Desarrolladora> = ArrayList();
        while(results.next()) {
            list.add(loadFromResultSet(results));
        }
        return list;
    }

    override fun update(obj: Desarrolladora): Boolean {
        if (obj.id == null) {
            return false
        }
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlUpdate);

        statement.setString(1,obj.nombre)
        statement.setString(2,obj.ubicacion)
        statement.setInt(3, obj.anioCreacion)
        statement.setString(4,obj.paginaWeb)
        statement.setString(5,if(obj.esIndependiente) "S" else "N")
        statement.setInt(6,obj?.id)
        val res = statement.executeUpdate();
        return res != 0
    }

    override fun delete(id: Int): Boolean {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlDelete);
        statement.setInt(1,id)
        return statement.executeUpdate() != 0
    }

    override fun save(obj: Desarrolladora): Boolean {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlInsert);
        statement.setString(1,obj.nombre)
        statement.setString(2,obj.ubicacion)
        statement.setInt(3, obj.anioCreacion)
        statement.setString(4,obj.paginaWeb)
        statement.setString(5,if(obj.esIndependiente) "S" else "N")
        val res = statement.executeUpdate();
        return res != 0
    }

    fun getByName(name: String): ArrayList<Desarrolladora> {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlName)
        statement.setString(1,"%${name}%");
        val results: ResultSet = statement.executeQuery();
        val list: ArrayList<Desarrolladora> = ArrayList();
        while(results.next()) {
            list.add(loadFromResultSet(results));
        }
        return list;
    }
}

class VideojuegoDAO: SQLDAO<Videojuego, Int>() {
    private val sqlId: String = "select * from videojuegos where id=?";
    private val sqlInsert: String = "insert into videojuegos (nombre, calificacion, fecha, generos, plataformas, desarrolladora) values (?,?,?,?,?,?);";
    private val sqlAll: String = "select * from videojuegos"
    private val sqlUpdate = "update videojuegos set nombre=?, calificacion=?, fecha=?, generos=?, plataformas=?, desarrolladora=? where id=?"
    private val sqlDelete = "delete from videojuegos where id=?"
    override fun getById(id: Int): Videojuego? {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlId);
        statement.setInt(1, id);
        val result: ResultSet = statement.executeQuery()
        result.next();
        if (result.row == 0) {
            return null;
        }
        return loadFromResultSet(result)
    }

    override fun loadFromResultSet(resultSet: ResultSet): Videojuego {
        val obj = Videojuego(
            nombre = resultSet.getString("nombre"),
            calificacion = resultSet.getDouble("calificacion"),
            fechaLanzamiento = LocalDate.parse(resultSet.getString("fecha"), Formats.dateFormat),
            desarrolladora = Desarrolladora.dao.getById(resultSet.getInt("desarrolladora")),
            id = resultSet.getInt("id")
        )
        obj.generos.addAll(Genero.toList(resultSet.getString("generos")))
        obj.plataformas.addAll(Plataforma.toList(resultSet.getString("plataformas")))
        return obj
    }

    override fun getAll(): ArrayList<Videojuego> {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlAll)
        val results: ResultSet = statement.executeQuery();
        val list: ArrayList<Videojuego> = ArrayList();
        while(results.next()) {
            list.add(loadFromResultSet(results));
        }
        return list;
    }

    override fun update(obj: Videojuego): Boolean {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlUpdate);
        //update videogames set title=?, rating=?, year=?, genres=?, platforms=?, company=? where id=?
        if(obj.id == null) {
            return false
        }
        obj.desarrolladora?.id?.let {
            statement.setString(1,obj.nombre)
            statement.setDouble(2, obj.calificacion)
            statement.setString(3, obj.fechaLanzamiento.format(Formats.dateFormat))
            statement.setString(4,obj.generos.joinToString(separator = ";"){it.key})
            statement.setString(5,obj.plataformas.joinToString(separator = ";"){it.id})
            statement.setInt(6, it)
            statement.setInt(7, obj.id)
            return statement.executeUpdate() != 0
        }
        return false
    }

    override fun delete(id: Int): Boolean {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlDelete);
        statement.setInt(1,id)
        return statement.executeUpdate() != 0
    }

    override fun save(obj: Videojuego): Boolean {
        val statement: PreparedStatement = DB.connection.prepareStatement(sqlInsert);
        //insert into videogames (title, rating, "year", genres, platforms, company) values (?,?,?,?,?,?);
        obj.desarrolladora?.id?.let { it2 ->
            statement.setString(1, obj.nombre)
            statement.setDouble(2,obj.calificacion)
            statement.setString(3,obj.fechaLanzamiento.format(Formats.dateFormat))
            statement.setString(4, obj.generos.joinToString(separator = ";") {it.key})
            statement.setString(5, obj.plataformas.joinToString(separator = ";"){it.id})
            statement.setInt(6, it2)

            return statement.executeUpdate() != 0;
        }
        return false
    }

}
