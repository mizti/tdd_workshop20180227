import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("FizzBuzzクラス")
class FizzBuzzTest {
	private FizzBuzz fizzbuzz;

	@BeforeEach
	void 前準備() {
		fizzbuzz = new FizzBuzz();
	}

	@Nested
	class _3の倍数の時は数の代わりにBuzzを返す{
		@Test
		void _3を渡すと文字列Fizzを返す() throws Exception {
			assertEquals("Fizz", fizzbuzz.convert(3));
		}
	}

	@Nested
	class _5の倍数の時は数の代わりにBuzzを返す{
		@Test
		void _5を渡すと文字列Bizzを返す() throws Exception {
			assertEquals("Buzz", fizzbuzz.convert(5));
		}
	}

	@Nested
	class _その他の数の場合は文字列にして返す{
		@Test
		void _1を渡すと文字列1を返す() throws Exception {
			assertEquals("1", fizzbuzz.convert(1));
		}
	}
}
