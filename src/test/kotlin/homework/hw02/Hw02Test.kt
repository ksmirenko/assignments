// ����� ��� ������� 02 �� 15.09.2015 (������������ ����)
// �����: ������ ��������, ������ 271
package homework.hw02

import java.util.Random
import org.junit.Test
import kotlin.test.assertEquals

class CustomRandom(f : () -> Double) : Random() {
    val nd : () -> Double = f

    override fun nextDouble() : Double {
        return nd()
    }
}

public class Hw02Test {
    @Test fun testZeroInfection() {
        // ���� �������� ����, ��� ������ �� ����������������
        val net = Network(
                arrayOf("Windows", "Android", "Mac OS", "Linux"),
                listOf(Pair(0, 1), Pair(1, 2), Pair(2, 3)),
                listOf(1),
                CustomRandom({ () -> 1.0})
        )
        // ����� 100 ����� ����� �� �������
        for (i in 1..100) net.tick()
        assertEquals("0100", net.getInfectionMap())
    }

    @Test fun testTotalInfection() {
        // ���� �������� ����, ��� ������ ���������������� ������
        val net = Network(
                arrayOf("Windows", "Android", "Mac OS", "Linux", "Windows"),
                listOf(Pair(0, 1), Pair(1, 2), Pair(2, 3), Pair(3, 4)),
                listOf(1),
                CustomRandom({ () -> 0.0})
        )
        // ����� 1 ������ ���������� ��������� ������
        net.tick()
        assertEquals("11100", net.getInfectionMap())
        // ��� ����� 1 ������ ���������� ���, ����� ����������
        net.tick()
        assertEquals("11110", net.getInfectionMap())
        // ��� ����� 1 ������ �������� ���
        net.tick()
        assertEquals("11111", net.getInfectionMap())
    }

    @Test fun testTotalInfectionBranchy() {
        // ���� ������������� ����, ��� ������ ���������������� ������
        val net = Network(
                arrayOf("Windows", "Linux", "Windows", "Android", "Mac OS", "Windows", "Windows"),
                listOf(Pair(0, 1), Pair(0, 3), Pair(1, 4), Pair(3, 4), Pair(4, 5), Pair(2, 5), Pair(4, 6)),
                listOf(3),
                CustomRandom({ () -> 0.0})
        )
        // ����� 1 ������ �������� 3 ����������
        net.tick()
        assertEquals("1001100", net.getInfectionMap())
        // ����� 2 ������ �������� 6 �����������
        net.tick()
        assertEquals("1101111", net.getInfectionMap())
        // ����� 3 ������ �������� ���
        net.tick()
        assertEquals("1111111", net.getInfectionMap())
    }
}