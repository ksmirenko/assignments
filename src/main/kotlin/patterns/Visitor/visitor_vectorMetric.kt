package patterns.Visitor


interface Visitor {
    fun visit(p: Point2d)
    fun visit(p: Point3d)
}

abstract class Point {
    abstract fun accept(v: Visitor)
    private var metric: Double = 0.0
    fun getMetric(): Double {
        return metric;
    }

    fun setMetric(metric: Double) {
        this.metric = metric;
    }
}

class Point2d(private var x : Double, private var y : Double) : Point() {

    override fun accept(v: Visitor) {
        v.visit(this)
    }

    fun getX(): Double {
        return x
    }

    fun getY(): Double {
        return y
    }
}

class Point3d(private var x : Double, private var y : Double, private var z : Double) : Point() {

    override fun accept(v: Visitor) {
        v.visit(this)
    }

    fun getX(): Double {
        return x
    }

    fun getY(): Double {
        return y
    }

    fun getZ(): Double {
        return z
    }
}

class Euclid() : Visitor {
    override fun visit(p: Point2d) {
        p.setMetric(Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY()))
    }

    override fun visit(p: Point3d) {
        p.setMetric(Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY() + p.getZ() * p.getZ()))
    }
}

class Chebyshev() : Visitor {
    override fun visit(p: Point2d) {
        val ax: Double = Math.abs(p.getX())
        val ay: Double = Math.abs(p.getY())
        p.setMetric(Math.max(ax, ay))
    }

    override fun visit(p: Point3d) {
        val ax: Double = Math.abs(p.getX())
        val ay: Double = Math.abs(p.getY())
        val az: Double = Math.abs(p.getZ())
        val max: Double = Math.max(Math.max(ax, ay), az)
        p.setMetric(max);
    }
}

fun main(args: Array<String>) {
    val p: Point = Point2d(3.0, 4.0)
    var v: Visitor = Chebyshev()
    p.accept(v)
    println("Chebyshev metric of (${(p as Point2d).getX()}, ${p.getY()}) is ${p.getMetric()}")
    v = Euclid()
    p.accept(v)
    println("Euclid metric of (${p.getX()}, ${p.getY()}) is ${p.getMetric()}")
}