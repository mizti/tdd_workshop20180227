import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

@DisplayName("Semverクラス")
class SemverTest {
	@Nested
	class コンストラクタ {
		@Nested
		class majorフィールド {
			@Test
			void majorフィールドに0を指定できる() {
				Semver semver = new Semver(0,1,1);
			}

			@Test
			void majorフィールドに3を指定できる() {
				Semver semver = new Semver(3,1,1);
			}

			@DisplayName("majorフィールドに-1を指定できない")
			@Test
			void major_field_should_not_accept_minus() {
				assertThrows(IllegalArgumentException.class, 
						() -> {
							Semver semver = new Semver(-1, 1, 1);
						});
			}
		}

		@Nested
		class minorフィールド {
			@Test
			void minorフィールドに0を指定できる() {
				Semver semver = new Semver(1,0,1);
			}

			@Test
			void minorフィールドに3を指定できる() {
				Semver semver = new Semver(1,3,1);
			}

			@DisplayName("minorフィールドに-1を指定できない")
			@Test
			void minor_field_should_not_accept_minus() {
				assertThrows(IllegalArgumentException.class,
							() -> {
								Semver semver = new Semver(1,-1,1);
							});
			}
		}

		@Nested
		class patchフィールド {
			@Test
			void patchフィールドに0を指定できる() {
				Semver semver = new Semver(1,1,0);
			}

			@Test
			void patchフィールドに3を指定できる() {
				Semver semver = new Semver(1,1,3);
			}

			@DisplayName("patchフィールドに-1を指定できない")
			@Test
			void patch_field_should_not_accept_minus() {
				assertThrows(IllegalArgumentException.class,
							() -> {
								Semver semver = new Semver(1,1,-1);
							});
			}
		}

		@Nested
		class toStringメソッドを持つ {
			@Test
			@DisplayName("toString()は\"{major}.{minor}.{patch}\"という文字列を返す")
			void toStringTest() {
				Semver s = new Semver(1,2,3);
				assertEquals("1.2.3",s.toString());
			}
		}
		
		@Nested
		class compareToメソッドを持つ {
			@Nested
			class aとbのmajorを比べて大きい方が大となること {
				private Semver a, b, c;
				@BeforeEach
				void compareMajorInit() {
					a = new Semver(2,3,4);
					b = new Semver(1,3,4);
					c = new Semver(1,3,4);
				}
				
				@Test
				void aの方が大きい場合1を返却する() {
					assertEquals(1, a.compareTo(b));
				}
				@Test
				void aと等しい場合0を返却する() {
					assertEquals(0, b.compareTo(c));
				}
				@Test
				@DisplayName("aの方が小さい場合1を返却する")
				void a_isSmallerThan_b() {
					assertEquals(-1, b.compareTo(a));
				}
			}

			@Nested
			class aとbのmajorが同じ場合minorを比べて大きい方が大となること {
				private Semver a, b, c;
				@BeforeEach
				void compareMinorInit() {
					a = new Semver(1,3,4);
					b = new Semver(1,2,4);
					c = new Semver(1,2,4);
				}
				
				@Test
				void aの方が大きい場合1を返却する() {
					assertEquals(1, a.compareTo(b));
				}
				@Test
				void aと等しい場合0を返却する() {
					assertEquals(0, b.compareTo(c));
				}
				@Test
				@DisplayName(" aの方が小さい場合-1を返却する")
				void a_isSmallerThan_b() {
					assertEquals(-1, b.compareTo(a));
				}
			}

			@Nested
			class aとbのmajorとminorが同じ場合にはpatchを比べて大きい方が大となること {
				private Semver a, b, c;
				@BeforeEach
				void comparePatchInit() {
					a = new Semver(1,2,4);
					b = new Semver(1,2,3);
					c = new Semver(1,2,3);
				}				
				
				@Test
				void aの方が大きい場合1を返却する() {
					assertEquals(1, a.compareTo(b));
				}
				@Test
				void aと等しい場合0を返却する() {
					assertEquals(0, b.compareTo(c));
				}
				@Test
				@DisplayName("aの方が小さい場合1を返却する")
				void a_isSmallerThan_b() {
					assertEquals(-1, b.compareTo(a));
				}
			}			
			
			@Nested
			class equalsメソッドを持つ {
				@Test
				void 等しい場合trueを返却する() {
					Semver a = new Semver(1,3,4);
					Semver b = new Semver(1,3,4);
					
					assertEquals(true, a.equals(b));
				}
				
				@Test
				void 等しくない場合falseを返却する() {
					Semver a = new Semver(1,4,5);
					Semver b = new Semver(1,4,7);
					
					assertEquals(false, a.equals(b));
				}
			}
			
			@Nested
			class getMajorメソッドを持つ {
				@Nested
				class インスタンスが持つMajorが数値として返却される{
					@Test
					@DisplayName("2.5.3の場合2が返却される")
					void returnsMajor(){
						Semver s = new Semver(2, 5, 3);
						assertEquals(2, s.getMajor());
					}
				}
			}

			@Nested
			class getMinorメソッドを持つ {
				@Nested
				class インスタンスが持つMinorが数値として返却される{
					@Test
					@DisplayName("2.5.3の場合5が返却される")
					void returnsMinor(){
						Semver s = new Semver(2, 5, 3);
						assertEquals(5, s.getMinor());
					}
				}
			}			
			
			@Nested
			class getPatchメソッドを持つ {
				@Nested
				class インスタンスが持つPatchが数値として返却される{
					@Test
					@DisplayName("2.5.3の場合3が返却される")
					void returnsMinor(){
						Semver s = new Semver(2, 5, 3);
						assertEquals(3, s.getPatch());
					}
				}
			}			
			
			@Nested
			class majorUpdateメソッドを持つ{
				private Semver s;
				@BeforeEach
				void majorUpdateInit(){
					s = new Semver(2, 5, 3);
					s.majorUpdate();					
				}
				
				@Test
				void majorUpdateするとmajorが1上がる() {
					assertEquals(3, s.getMajor());
				}
				@Test
				void majorUpdateするとminorが0になる() {
					assertEquals(0, s.getMinor());
				}
				@Test
				void majorUpdateするとpatchが0になる() {
					assertEquals(0, s.getPatch());
				}				
			}

			@Nested
			class minorUpdateメソッドを持つ{
				private Semver s;
				@BeforeEach
				void minorUpdateInit() {
					s = new Semver(2, 5, 3);
					s.minorUpdate();
				}
				@Test
				void minorUpdateするとmajorは変わらない() {
					assertEquals(2, s.getMajor());
				}

				void minorUpdateするとminorが1上がる() {
					assertEquals(6, s.getMinor());
				}				

				void minorUpdateするとpatchが0になる() {
					assertEquals(0, s.getPatch());
				}				
			
			}			
		
			@Nested
			class patchUpdateメソッドを持つ{
				private Semver s;
				@BeforeEach
				void patchUpdateInit() {
					s = new Semver(2, 5, 3);
					s.patchUpdate();
				}
				@Test
				void patchUpdateするとmajorは変わらない() {
					assertEquals(2, s.getMajor());
				}
				void patchUpdateするとminorは変わらない() {
					assertEquals(5, s.getMinor());
				}
				void patchUpdateするとpatchが1上がる() {
					assertEquals(4, s.getPatch());
				}			
			}			
		}	
	}
}