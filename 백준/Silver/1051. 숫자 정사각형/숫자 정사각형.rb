=begin
1. 정사각형 가능 size 길이를 구하고 순차적으로 내려가면 되륻ㅅ 만약 최대 사이즉 ㅏ있으면 break
2. 가로로 먼저 탐색하고 그 다음 세로로 탐색하자
=end

input = gets.chomp.split.map(&:to_i)
N = input[0]
M = input[1]

square = []
size = [N, M].min

# 입력
N.times do |i|
  data = gets.chomp.chars.map(&:to_i)
  square[i] = data
end
# 이중 for 문 끝내기위해서
flag = false
# 로직 실행
size.times do
  for i in 0..N - size
    for j in 0..M - size
      data_w = square[i]
      data_h = square[i + size - 1]
      leftTop = data_w[j]
      rightTop = data_w[j + size - 1]
      leftBottom = data_h[j]
      rightBottom = data_h[j + size - 1]
      if leftTop == rightTop && leftBottom == rightBottom && leftTop == rightBottom
        flag = true
        puts size * size
        break
      end
    end

    if flag
      break
    end
  end
  if flag
    break
  end
  size -=1
end
