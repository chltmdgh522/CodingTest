=begin
1. 스위치입력을 받는다.
2. 이차원리스트로 학생의 정보를 받는다. 받으면서 로직 실행하자 함수로
3. 함수에서는 for문을 활용하여 스위치를 동작하면 될듯 싶다.
=end

N = gets.chomp.to_i
# 스위치 입력
BUTTON = gets.chomp.split.map(&:to_i)

def solution(student)
  number = student[1]
  # 남학생
  if student[0] == 1
    for i in (number..N).step(number)
      if BUTTON[i - 1] == 0
        BUTTON[i - 1] = 1
      else
        BUTTON[i - 1] = 0
      end
    end

    # 여학생
  else
    # 기준 비교 2씩 증가해야됨
    compare = 2
    # 기준 앞으로 탐색
    for j in (number - 1).step(1, -1)
      i = j - 1
      if i + compare >= N
        break
      end
      if BUTTON[i] == BUTTON[i + compare]
        if BUTTON[i] == 0
          BUTTON[i] = 1
          BUTTON[i + compare] = 1
        else
          BUTTON[i] = 0
          BUTTON[i + compare] = 0
        end
        compare += 2
      else
        break
      end

    end

    if BUTTON[number - 1] == 0
      BUTTON[number - 1] = 1
    else
      BUTTON[number - 1] = 0
    end
  end
end

# 학생 입력 받으면서 로직 수행
M = gets.chomp.to_i
M.times do |i|
  student = gets.chomp.split.map(&:to_i)
  solution(student)
end

BUTTON.each_slice(20) { |slice| puts slice.join(" ") }
