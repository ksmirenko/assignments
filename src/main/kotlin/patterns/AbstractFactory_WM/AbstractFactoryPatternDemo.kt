package patterns.AbstractFactory_WM

/**
 * @author Mikhail
 */

interface Shape {
    fun draw()
}

class Square: Shape {
    override fun draw() {
        println("Inside Square::draw() method.")
    }
}

class Rectangle: Shape {
    override fun draw() {
        println("Inside Rectangle::draw() method.")
    }
}

class Circle: Shape {
    override fun draw() {
        println("Inside Circle::draw() method.")
    }
}

interface Color {
    fun fill()
}

class Blue: Color {
    override fun fill() {
        println("Inside Blue::fill() method.")
    }
}

class Green: Color {
    override fun fill() {
        println("Inside Green::fill() method.")
    }
}

class Red: Color {
    override fun fill() {
        println("Inside Red::fill() method.")
    }
}

abstract class AbstractFactory {
    abstract fun getColor(color: String): Color?
    abstract fun getShape(shape: String): Shape?
}

class ShapeFactory: AbstractFactory() {
    override fun getShape(shape: String): Shape? {
        when (shape) {
            "CIRCLE"    -> return Circle()
            "RECTANGLE" -> return Rectangle()
            "SQUARE"    -> return Square()
        }
        return null
    }
    override fun getColor(color: String): Color? {
        return null
    }
}

class ColorFactory: AbstractFactory() {
    override fun getShape(shape: String): Shape? {
        return null
    }
    override fun getColor(color: String): Color? {
        when (color) {
            "RED"   -> return Red()
            "GREEN" -> return Green()
            "BLUE"  -> return Blue()
        }
        return null
    }
}

object FactoryProducer {
    fun getFactory(choice: String): AbstractFactory? {
        when (choice) {
            "SHAPE" -> return ShapeFactory()
            "COLOR" -> return ColorFactory()
        }
        return null
    }
}

fun main(args: Array<String>) {
      val shapeFactory = FactoryProducer.getFactory("SHAPE")
      val shape1 = shapeFactory?.getShape("CIRCLE")
      shape1?.draw()
      val shape2 = shapeFactory?.getShape("RECTANGLE")
      shape2?.draw()
      val shape3 = shapeFactory?.getShape("SQUARE")
      shape3?.draw()
      val colorFactory = FactoryProducer.getFactory("COLOR")
      val color1 = colorFactory?.getColor("RED")
      color1?.fill()
      val color2 = colorFactory?.getColor("GREEN")
      color2?.fill()
      val color3 = colorFactory?.getColor("BLUE")
      color3?.fill()
 }
