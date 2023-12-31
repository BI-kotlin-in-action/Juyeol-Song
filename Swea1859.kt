package ps.ps

import java.util.Stack

/**
 * @author : Unagi_zoso
 * @date : 2023-11-07
 */
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc

/**
 * 입력 :
 * 맨 처음 총 테스트 케이스
 * 현재 케이스에 주어질 원소의 개수 (사용되지 않아 변수 없이 버려진다.)
 * 현재 케이스의 원소들이 ' '으로 구분되어 한 줄에 입력된다.
 *
 * 해결로직:
 * 주어진 배열의 끝 원소부터 시작 원소로 접근한다. (stack으로 해결)
 * 현재의 원소보다 작거나 같은 원소들은 반드시 이득을 보기에
 * 이전의 원소 중 현재의 원소보다 작거나 같으면 새로운 임시 배열을 만들어 저장한다.
 * 현재의 원소보다 큰 원소를 만나면 멈추고 임시 배열의 원소들과 현재 원소의 값의 차를 ans에 더한다.
 * stack의 모든 원소를 보았다면 결과를 출력한다. 출력형식 ex) #1 45
 */
fun main() = System.`in`.bufferedReader().use { reader ->
    System.out.bufferedWriter().use { writer ->
        repeat(reader.readLine().toInt()) {
            reader.readLine().toInt() // useless
            val stk = Stack<Int>().apply {
                this.addAll(reader.readLine().split(" ").map { num -> num.toInt() })
            }

            var sumAllBenefits = 0
            while (stk.isNotEmpty()) {
                val li = mutableListOf<Int>(stk.pop()) // 현재의 원소를 첫 번째 원소로 가진 임시 배열을 만든다.
                extractLesserOrEqual(stk, li)
                sumAllBenefits += calculateGapWithFirst(li)
            }
            writer.write("#${it + 1} $sumAllBenefits\n")
        }
    }
}

// 현재의 원소보다 작거나 같은 원소들을 stk에서 li로 옮겨담는다.
private fun extractLesserOrEqual(stk: Stack<Int>, li: MutableList<Int>) {
    while (stk.isNotEmpty() && stk.last() <= li.first()) {
        li.add(stk.pop())
    }
}

// 첫 번째 원소(현재의 원소)와 다른 원소들의 차이를 합하여 반환한다.
private fun calculateGapWithFirst(li: List<Int>): Int {
    return li.sumOf { li[0] - it }
}
