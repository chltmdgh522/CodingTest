T = gets.chomp.to_i

for i in 0...T
  message = gets.chomp.to_s
  count = gets.chomp.to_i

  if count == 1
    puts "1 1"
    next
  end

  positions = Hash.new { |h, k| h[k] = [] }  # 각 문자의 위치 저장

  # 각 문자의 등장 인덱스를 저장
  message.chars.each_with_index do |char, idx|
    positions[char] << idx
  end

  min = 999999
  max = -1

  # 각 문자에 대해 최소, 최대 길이 계산
  positions.each do |char, indices|
    next if indices.length < count  # 등장 횟수가 count보다 적으면 무시

    # 투 포인터처럼 count만큼 떨어진 위치를 확인하여 길이 계산
    for j in 0...(indices.length - count + 1)
      length = indices[j + count - 1] - indices[j] + 1
      min = [min, length].min
      max = [max, length].max
    end
  end

  if min == 999999 || max == -1
    puts "-1"
  else
    puts "#{min} #{max}"
  end
end
