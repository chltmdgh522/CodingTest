=begin
1. 입력을 받고 저장하자.
2. 모든 경우의 수를 다 넣어서 검증을 해보자
3.
=end

# 입력
N = gets.chomp.to_i
input = []
N.times do
  data = gets.chomp.split.map(&:to_i)
  input.push(data)
end

def solution(i, j, k, input,n)
  cnt = 0

  for l in 0...n
    s = 0
    b = 0
    data = input[l]
    num = data[0].to_s
    # 첫자리
    if i.to_s == num[0]
      s += 1
    elsif i.to_s == num[1] || i.to_s == num[2]
      b += 1
    end

    # 두자리
    if j.to_s == num[1]
      s += 1
    elsif j.to_s == num[0] || j.to_s == num[2]
      b += 1
    end

    # 마지막자리
    if k.to_s == num[2]
      s += 1
    elsif k.to_s == num[1] || k.to_s == num[0]
      b += 1
    end

    if s == data[1] && b == data[2]
      cnt += 1
    else
      return 0
    end
  end

  return cnt
end

# 정답 찾기
result = 0
# 모든 경우의 수 판단
for i in 1..9
  for j in 1..9
    if i == j
      next
    end
    for k in 1..9
      if j == k || i == k
        next
      end
      answer = solution(i, j, k, input,N)

      if answer == N
        result += 1
      end
    end
  end
end

puts result

