package ulloa.kenneth.gameswiki.model.dao

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

interface DAO<T, K> {
    fun getById(id: K): T?
    fun getAll(): ArrayList<T>
    fun save(obj: T): Boolean
    fun delete(id: K): Boolean
    fun update(obj: T): Boolean
}

abstract class SQLDAO<T, K>: DAO<T, K> {
    fun getStatement(sql: String): PreparedStatement {
        return DB.connection.prepareStatement(sql);
    }
    protected abstract fun loadFromResultSet(resultSet: ResultSet): T
}

class DB {
    companion object {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:database.db");
    }
}