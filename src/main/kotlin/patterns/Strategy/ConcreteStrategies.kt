package patterns.Strategy
import java.security.MessageDigest;
class MD5 () : IHasher{
    override fun computeHash(password : String) : String {
        val md = MessageDigest.getInstance("MD5")
        md.update(password.toByteArray())
        val byteData = md.digest()
        val sb = StringBuilder()
        for (b in byteData) {
            sb.append("%02x".format(b))
        }
        return sb.toString()
    }
}
class SHA256 (): IHasher{
    override fun computeHash(password : String) : String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(password.toByteArray())
        val byteData = md.digest()
        val sb = StringBuilder()
        for (b in byteData) {
            sb.append("%02x".format(b))
        }
        return sb.toString()
    }
}