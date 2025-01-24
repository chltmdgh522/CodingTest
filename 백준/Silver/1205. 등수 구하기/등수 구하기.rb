# frozen_string_literal: true

N, S, P = gets.chomp.split.map(&:to_i)
score = []
if N != 0
  score = gets.chomp.split.map(&:to_i)
end

if P != 0
  index_of = 1
else
  index_of = 0
end
cnt = 0
if score.index(S).nil?
  for i in 0...N
    cnt += 1
    if S > score[i]
      index_of = cnt
      break
    end
    if i == N - 1
      if cnt + 1 <= P
        index_of = cnt +1
      else
        index_of = -1
      end
    end
  end
else
  index_of = score.index(S) + 1
end



if S == score[N - 1] && index_of >= P
  index_of = -1

elsif S == score[N - 1] && N == P
  index_of = -1
end

puts index_of
